/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.parsetree.formatter;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.formatting.IElementMatcherProvider;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IAfterElement;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IBeforeElement;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IBetweenElements;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IElementMatcher;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IElementPattern;
import org.eclipse.xtext.formatting.impl.AbstractTokenStream;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.parsetree.formatter.services.ElementMatcherTestLanguageGrammarAccess;
import org.eclipse.xtext.parsetree.formatter.services.ElementMatcherTestLanguageGrammarAccess.LoopElements;
import org.eclipse.xtext.parsetree.formatter.services.ElementMatcherTestLanguageGrammarAccess.OptionalCallsElements;
import org.eclipse.xtext.parsetree.formatter.services.ElementMatcherTestLanguageGrammarAccess.RuleCallsElements;
import org.eclipse.xtext.parsetree.formatter.services.ElementMatcherTestLanguageGrammarAccess.SimpleElements;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.collect.Lists;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
public class ElementMatcherTest extends AbstractXtextTests {
	private ElementMatcherTestLanguageGrammarAccess g;

	private class Patterns {
		private List<IElementPattern> patterns = Lists.newArrayList();

		public void after(final AbstractElement ele) {
			patterns.add(new IAfterElement() {
				public AbstractElement matchAfter() {
					return ele;
				}

				@Override
				public String toString() {
					return "!";
				}
			});
		}

		public void before(final AbstractElement ele) {
			patterns.add(new IBeforeElement() {
				public AbstractElement matchBefore() {
					return ele;
				}

				@Override
				public String toString() {
					return "!";
				}
			});
		}

		public void between(final AbstractElement first, final AbstractElement second) {
			patterns.add(new IBetweenElements() {
				public Pair<AbstractElement, AbstractElement> matchBetween() {
					return Tuples.create(first, second);
				}

				@Override
				public String toString() {
					return "!";
				}
			});
		}
	}

	private String match(String model, Patterns patterns) throws Exception {
		EObject m = getModel(model);
		IElementMatcherProvider mp = get(IElementMatcherProvider.class);
		final IElementMatcher<IElementPattern> matcher = mp.createMatcher(patterns.patterns);
		final StringBuilder result = new StringBuilder();
		getParseTreeConstructor().serializeSubtree(m, new AbstractTokenStream() {
			@Override
			public void writeSemantic(EObject grammarElement, String value) throws IOException {
				Iterable<IElementPattern> matches = matcher.matchNext((AbstractElement) grammarElement);
				for (IElementPattern m : matches) {
					result.append(" ");
					result.append(m.toString());
				}
				result.append(" ");
				result.append(value);
			}
		});
		for (IElementPattern m2 : matcher.finish()) {
			result.append(" ");
			result.append(m2.toString());
		}
		//		String file = "src-gen/" + getClass().getName().replace('.', '/') + "-" + getName() + ".pdf";
		//		new TransitionMatcherToDot().draw(matcher, file, "-T pdf");
		return result.toString().trim();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(ElementMatcherTestLanguageStandaloneSetup.class);
		g = (ElementMatcherTestLanguageGrammarAccess) getGrammarAccess();
	}

	public void testSimple1() throws Exception {
		Patterns pattern = new Patterns();
		pattern.after(g.getSimpleAccess().getNumberSignDigitOneKeyword_0());
		pattern.before(g.getSimpleAccess().getKw1Keyword_2_0());
		assertEquals("#1 ! abc ! kw1 efg long.name", match("#1 abc kw1 efg long.name", pattern));
	}

	public void testSimpleAssignments() throws Exception {
		Patterns pattern = new Patterns();
		pattern.after(g.getSimpleAccess().getNameAssignment_1());
		pattern.before(g.getSimpleAccess().getOptionalAssignment_2_1());
		pattern.before(g.getSimpleAccess().getDatatypeAssignment_3());
		assertEquals("#1 abc ! kw1 ! efg ! long.name", match("#1 abc kw1 efg long.name", pattern));
	}

	public void testSimpleGroup1() throws Exception {
		Patterns pattern = new Patterns();
		pattern.before(g.getSimpleAccess().getGroup_2());
		assertEquals("#1 abc ! kw1 efg long.name", match("#1 abc kw1 efg long.name", pattern));

		pattern = new Patterns();
		pattern.after(g.getSimpleAccess().getGroup_2());
		assertEquals("#1 abc kw1 efg ! long.name", match("#1 abc kw1 efg long.name", pattern));

		pattern = new Patterns();
		pattern.before(g.getSimpleAccess().getGroup());
		pattern.after(g.getSimpleAccess().getGroup());
		assertEquals("! #1 abc kw1 efg long.name !", match("#1 abc kw1 efg long.name", pattern));
	}

	public void testSimpleBetween() throws Exception {
		SimpleElements se = g.getSimpleAccess();
		Patterns pattern = new Patterns();
		pattern.between(se.getNumberSignDigitOneKeyword_0(), se.getNameIDTerminalRuleCall_1_0());
		assertEquals("#1 ! abc kw1 efg long.name", match("#1 abc kw1 efg long.name", pattern));

		pattern = new Patterns();
		pattern.between(se.getNumberSignDigitOneKeyword_0(), se.getNameAssignment_1());
		assertEquals("#1 ! abc kw1 efg long.name", match("#1 abc kw1 efg long.name", pattern));

		pattern = new Patterns();
		pattern.between(se.getNameIDTerminalRuleCall_1_0(), se.getDatatypeAssignment_3());
		assertEquals("#1 abc ! long.name", match("#1 abc long.name", pattern));
		assertEquals("#1 abc kw1 efg long.name", match("#1 abc kw1 efg long.name", pattern));

		pattern = new Patterns();
		pattern.between(se.getGroup_2(), se.getDatatypeFQNParserRuleCall_3_0());
		assertEquals("#1 abc long.name", match("#1 abc long.name", pattern));
		assertEquals("#1 abc kw1 efg ! long.name", match("#1 abc kw1 efg long.name", pattern));
	}

	public void testRuleCalls1() throws Exception {
		RuleCallsElements rce = g.getRuleCallsAccess();
		Patterns pattern = new Patterns();
		pattern.before(rce.getRuleCallsSubParserRuleCall_1());
		assertEquals("#2 ! sub foo", match("#2 sub foo", pattern));

		pattern = new Patterns();
		pattern.between(rce.getRuleCallsSubParserRuleCall_1(), rce.getNameAssignment_2());
		assertEquals("#2 sub ! foo", match("#2 sub foo", pattern));

		pattern = new Patterns();
		pattern.between(rce.getNameAssignment_2(), rce.getCall1Assignment_3());
		pattern.between(rce.getNameAssignment_2(), rce.getCall2Assignment_4());
		assertEquals("#2 sub foo ! ass1 bar ass2 zonk", match("#2 sub foo ass1 bar ass2 zonk", pattern));
		assertEquals("#2 sub foo ! ass2 zonk", match("#2 sub foo ass2 zonk", pattern));

		pattern = new Patterns();
		pattern.before(g.getRuleCallsSubAccess().getSubAssignment());
		pattern.after(g.getRuleCallsSubAccess().getSubAssignment());
		assertEquals("#2 ! sub ! foo", match("#2 sub foo", pattern));

		pattern = new Patterns();
		pattern.before(g.getRuleCallsAss1Access().getGroup());
		pattern.after(g.getRuleCallsAss1Access().getGroup());
		assertEquals("#2 sub foo ! ass1 foo !", match("#2 sub foo ass1 foo", pattern));

		pattern = new Patterns();
		pattern.before(g.getRuleCallsAss2Access().getGroup());
		pattern.after(g.getRuleCallsAss2Access().getGroup());
		assertEquals("#2 sub foo ass1 bar ! ass2 zonk !", match("#2 sub foo ass1 bar ass2 zonk", pattern));
	}

	public void testOptionalCalls() throws Exception {
		OptionalCallsElements oce = g.getOptionalCallsAccess();
		Patterns pattern = new Patterns();
		pattern.between(oce.getNumberSignDigitThreeKeyword_0(), oce.getNameAssignment_3());
		assertEquals("#3 ! foo", match("#3 foo", pattern));

		pattern = new Patterns();
		pattern.between(oce.getNumberSignDigitThreeKeyword_0(), g.getOptionalCallsSub1Access().getSubKeyword_2());
		pattern.between(g.getOptionalCallsSub1Access().getSubKeyword_2(), oce.getNameAssignment_3());
		assertEquals("#3 ! sub ! foo", match("#3 sub foo", pattern));

		pattern = new Patterns();
		pattern.between(oce.getNumberSignDigitThreeKeyword_0(), g.getOptionalCallsSub2Access().getSub2Keyword_0());
		pattern.between(g.getOptionalCallsSub3Access().getNameAssignment_1(), oce.getNameAssignment_3());
		assertEquals("#3 ! sub2 foo sub sub3 bar ! baz", match("#3 sub2 foo sub sub3 bar baz", pattern));
	}

	public void testRecursion() throws Exception {
		Patterns p = new Patterns();
		p.after(g.getRecursionSubAccess().getLeftCurlyBracketKeyword_1());
		p.before(g.getRecursionSubAccess().getRightCurlyBracketKeyword_3());
		assertEquals("#4 { ! bar { ! foo ! } { ! { ! zonk ! } ! } ! }", match("#4 { { foo } bar { { zonk } } }", p));
	}

	public void testLoop() throws Exception {
		LoopElements le = g.getLoopAccess();
		Patterns p = new Patterns();
		p.before(le.getNamesAssignment_1());
		assertEquals("#5 ! foo ! bar ! baz", match("#5 foo bar baz", p));

		p = new Patterns();
		p.after(le.getNamesAssignment_1());
		assertEquals("#5 foo ! bar ! baz !", match("#5 foo bar baz", p));

		p = new Patterns();
		p.between(le.getNamesAssignment_1(), le.getNamesAssignment_1());
		assertEquals("#5 foo ! bar ! baz", match("#5 foo bar baz", p));

		p = new Patterns();
		p.before(le.getGroup_2());
		assertEquals("#5 foo bar ! gr grfoo ! gr grbar", match("#5 foo bar gr grfoo gr grbar", p));

		p = new Patterns();
		p.after(le.getGroup_2());
		assertEquals("#5 foo bar gr grfoo ! gr grbar !", match("#5 foo bar gr grfoo gr grbar", p));

		p = new Patterns();
		p.between(le.getGroup_2(), le.getGroup_2());
		assertEquals("#5 foo bar gr grfoo ! gr grbar", match("#5 foo bar gr grfoo gr grbar", p));

		p = new Patterns();
		p.between(le.getNamesAssignment_1(), le.getGroup_2());
		assertEquals("#5 foo bar ! gr grfoo gr grbar", match("#5 foo bar gr grfoo gr grbar", p));

		p = new Patterns();
		p.before(le.getAlternatives_3());
		assertEquals("#5 x gr gf gr gb ! '1' ! '2' ! 1 ! 2", match("#5 x gr gf gr gb '1' '2' 1 2", p));

		p = new Patterns();
		p.after(le.getAlternatives_3());
		assertEquals("#5 x gr gf gr gb '1' ! '2' ! 1 ! 2 !", match("#5 x gr gf gr gb '1' '2' 1 2", p));

		p = new Patterns();
		p.between(le.getAlternatives_3(), le.getAlternatives_3());
		assertEquals("#5 x gr gf gr gb '1' ! '2' ! 1 ! 2", match("#5 x gr gf gr gb '1' '2' 1 2", p));

		p = new Patterns();
		p.between(le.getGroup_2(), le.getAlternatives_3());
		assertEquals("#5 x gr gf gr gb ! '1' '2' 1 2", match("#5 x gr gf gr gb '1' '2' 1 2", p));

		p = new Patterns();
		p.between(le.getNamesAssignment_1(), le.getAlternatives_3());
		assertEquals("#5 x gr gf gr gb '1' '2' 1 2", match("#5 x gr gf gr gb '1' '2' 1 2", p));
		assertEquals("#5 x ! '1' '2' 1 2", match("#5 x '1' '2' 1 2", p));
	}
}
