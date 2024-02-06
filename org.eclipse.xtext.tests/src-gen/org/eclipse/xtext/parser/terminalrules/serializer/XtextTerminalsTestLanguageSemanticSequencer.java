/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.terminalrules.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.terminalrules.services.XtextTerminalsTestLanguageGrammarAccess;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Alternatives;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Assignment;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.CharacterRange;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.CrossReference;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.EnumLiteralDeclaration;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.EnumRule;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.GeneratedMetamodel;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Grammar;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Group;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Keyword;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.NegatedToken;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.ReferencedMetamodel;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.RuleCall;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TerminalRule;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.TypeRef;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.UntilToken;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Wildcard;
import org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.XtextTerminalsTestLanguagePackage;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class XtextTerminalsTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private XtextTerminalsTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == XtextTerminalsTestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case XtextTerminalsTestLanguagePackage.ACTION:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_Action(context, (org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Action) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getActionRule()) {
					sequence_Action(context, (org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Action) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.ALTERNATIVES:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_Alternatives(context, (Alternatives) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAssignableTerminalRule()
						|| rule == grammarAccess.getParenthesizedAssignableElementRule()
						|| rule == grammarAccess.getAssignableAlternativesRule()
						|| action == grammarAccess.getAssignableAlternativesAccess().getAlternativesGroupsAction_1_0()) {
					sequence_AssignableAlternatives(context, (Alternatives) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getEnumLiteralsRule()) {
					sequence_EnumLiterals(context, (Alternatives) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_TerminalAlternatives_TerminalToken(context, (Alternatives) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.ASSIGNMENT:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_Assignment(context, (Assignment) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAssignmentRule()) {
					sequence_Assignment(context, (Assignment) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.CHARACTER_RANGE:
				if (rule == grammarAccess.getCharacterRangeRule()) {
					sequence_CharacterRange(context, (CharacterRange) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_CharacterRange_TerminalToken(context, (CharacterRange) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.CROSS_REFERENCE:
				sequence_CrossReference(context, (CrossReference) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.ENUM_LITERAL_DECLARATION:
				sequence_EnumLiteralDeclaration(context, (EnumLiteralDeclaration) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.ENUM_RULE:
				sequence_EnumRule(context, (EnumRule) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.GENERATED_METAMODEL:
				sequence_GeneratedMetamodel(context, (GeneratedMetamodel) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.GRAMMAR:
				sequence_Grammar(context, (Grammar) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.GROUP:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_Group(context, (Group) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_TerminalGroup_TerminalToken(context, (Group) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.KEYWORD:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_Keyword(context, (Keyword) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getKeywordRule()
						|| rule == grammarAccess.getAssignableTerminalRule()
						|| rule == grammarAccess.getParenthesizedAssignableElementRule()
						|| rule == grammarAccess.getAssignableAlternativesRule()
						|| action == grammarAccess.getAssignableAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getCrossReferenceableTerminalRule()
						|| rule == grammarAccess.getCharacterRangeRule()
						|| action == grammarAccess.getCharacterRangeAccess().getCharacterRangeLeftAction_1_0()) {
					sequence_Keyword(context, (Keyword) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_Keyword_TerminalToken(context, (Keyword) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.NEGATED_TOKEN:
				if (rule == grammarAccess.getAbstractNegatedTokenRule()
						|| rule == grammarAccess.getNegatedTokenRule()) {
					sequence_NegatedToken(context, (NegatedToken) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_NegatedToken_TerminalToken(context, (NegatedToken) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.PARSER_RULE:
				sequence_ParserRule(context, (org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.ParserRule) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.REFERENCED_METAMODEL:
				sequence_ReferencedMetamodel(context, (ReferencedMetamodel) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.RULE_CALL:
				if (rule == grammarAccess.getAlternativesRule()
						|| action == grammarAccess.getAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getGroupRule()
						|| action == grammarAccess.getGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getAbstractTokenRule()
						|| rule == grammarAccess.getAbstractTokenWithCardinalityRule()
						|| rule == grammarAccess.getAbstractTerminalRule()
						|| rule == grammarAccess.getParenthesizedElementRule()) {
					sequence_AbstractTokenWithCardinality_RuleCall(context, (RuleCall) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getRuleCallRule()
						|| rule == grammarAccess.getAssignableTerminalRule()
						|| rule == grammarAccess.getParenthesizedAssignableElementRule()
						|| rule == grammarAccess.getAssignableAlternativesRule()
						|| action == grammarAccess.getAssignableAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getCrossReferenceableTerminalRule()) {
					sequence_RuleCall(context, (RuleCall) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_RuleCall_TerminalToken(context, (RuleCall) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.TERMINAL_RULE:
				sequence_TerminalRule(context, (TerminalRule) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.TYPE_REF:
				sequence_TypeRef(context, (TypeRef) semanticObject); 
				return; 
			case XtextTerminalsTestLanguagePackage.UNTIL_TOKEN:
				if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_TerminalToken_UntilToken(context, (UntilToken) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAbstractNegatedTokenRule()
						|| rule == grammarAccess.getUntilTokenRule()) {
					sequence_UntilToken(context, (UntilToken) semanticObject); 
					return; 
				}
				else break;
			case XtextTerminalsTestLanguagePackage.WILDCARD:
				if (rule == grammarAccess.getTerminalAlternativesRule()
						|| action == grammarAccess.getTerminalAlternativesAccess().getAlternativesGroupsAction_1_0()
						|| rule == grammarAccess.getTerminalGroupRule()
						|| action == grammarAccess.getTerminalGroupAccess().getGroupTokensAction_1_0()
						|| rule == grammarAccess.getTerminalTokenRule()
						|| rule == grammarAccess.getTerminalTokenElementRule()
						|| rule == grammarAccess.getParenthesizedTerminalElementRule()) {
					sequence_TerminalToken_Wildcard(context, (Wildcard) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getWildcardRule()) {
					sequence_Wildcard(context, (Wildcard) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns Action
	 *     Alternatives.Alternatives_1_0 returns Action
	 *     Group returns Action
	 *     Group.Group_1_0 returns Action
	 *     AbstractToken returns Action
	 *     AbstractTokenWithCardinality returns Action
	 *     AbstractTerminal returns Action
	 *     ParenthesizedElement returns Action
	 *
	 * Constraint:
	 *     (type=TypeRef (feature=ID (operator='=' | operator='+='))? (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_Action(ISerializationContext context, org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Action semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns Alternatives
	 *     Alternatives.Alternatives_1_0 returns Alternatives
	 *     Group returns Alternatives
	 *     Group.Group_1_0 returns Alternatives
	 *     AbstractToken returns Alternatives
	 *     AbstractTokenWithCardinality returns Alternatives
	 *     AbstractTerminal returns Alternatives
	 *     ParenthesizedElement returns Alternatives
	 *
	 * Constraint:
	 *     (groups+=Alternatives_Alternatives_1_0 groups+=Group+ (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_Alternatives(ISerializationContext context, Alternatives semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns Assignment
	 *     Alternatives.Alternatives_1_0 returns Assignment
	 *     Group returns Assignment
	 *     Group.Group_1_0 returns Assignment
	 *     AbstractToken returns Assignment
	 *     AbstractTokenWithCardinality returns Assignment
	 *     AbstractTerminal returns Assignment
	 *     ParenthesizedElement returns Assignment
	 *
	 * Constraint:
	 *     (feature=ID (operator='+=' | operator='=' | operator='?=') terminal=AssignableTerminal (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_Assignment(ISerializationContext context, Assignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns Group
	 *     Alternatives.Alternatives_1_0 returns Group
	 *     Group returns Group
	 *     Group.Group_1_0 returns Group
	 *     AbstractToken returns Group
	 *     AbstractTokenWithCardinality returns Group
	 *     AbstractTerminal returns Group
	 *     ParenthesizedElement returns Group
	 *
	 * Constraint:
	 *     (tokens+=Group_Group_1_0 tokens+=AbstractToken+ (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_Group(ISerializationContext context, Group semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns Keyword
	 *     Alternatives.Alternatives_1_0 returns Keyword
	 *     Group returns Keyword
	 *     Group.Group_1_0 returns Keyword
	 *     AbstractToken returns Keyword
	 *     AbstractTokenWithCardinality returns Keyword
	 *     AbstractTerminal returns Keyword
	 *     ParenthesizedElement returns Keyword
	 *
	 * Constraint:
	 *     (value=STRING (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_Keyword(ISerializationContext context, Keyword semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Alternatives returns RuleCall
	 *     Alternatives.Alternatives_1_0 returns RuleCall
	 *     Group returns RuleCall
	 *     Group.Group_1_0 returns RuleCall
	 *     AbstractToken returns RuleCall
	 *     AbstractTokenWithCardinality returns RuleCall
	 *     AbstractTerminal returns RuleCall
	 *     ParenthesizedElement returns RuleCall
	 *
	 * Constraint:
	 *     (rule=[AbstractRule|ID] (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_AbstractTokenWithCardinality_RuleCall(ISerializationContext context, RuleCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Action returns Action
	 *
	 * Constraint:
	 *     (type=TypeRef (feature=ID (operator='=' | operator='+='))?)
	 * </pre>
	 */
	protected void sequence_Action(ISerializationContext context, org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.Action semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AssignableTerminal returns Alternatives
	 *     ParenthesizedAssignableElement returns Alternatives
	 *     AssignableAlternatives returns Alternatives
	 *     AssignableAlternatives.Alternatives_1_0 returns Alternatives
	 *
	 * Constraint:
	 *     (groups+=AssignableAlternatives_Alternatives_1_0 groups+=AssignableTerminal+)
	 * </pre>
	 */
	protected void sequence_AssignableAlternatives(ISerializationContext context, Alternatives semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Assignment returns Assignment
	 *
	 * Constraint:
	 *     (feature=ID (operator='+=' | operator='=' | operator='?=') terminal=AssignableTerminal)
	 * </pre>
	 */
	protected void sequence_Assignment(ISerializationContext context, Assignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     CharacterRange returns CharacterRange
	 *
	 * Constraint:
	 *     (left=CharacterRange_CharacterRange_1_0 right=Keyword)
	 * </pre>
	 */
	protected void sequence_CharacterRange(ISerializationContext context, CharacterRange semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.CHARACTER_RANGE__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.CHARACTER_RANGE__LEFT));
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.CHARACTER_RANGE__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.CHARACTER_RANGE__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getCharacterRangeAccess().getCharacterRangeLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getCharacterRangeAccess().getRightKeywordParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns CharacterRange
	 *     TerminalAlternatives.Alternatives_1_0 returns CharacterRange
	 *     TerminalGroup returns CharacterRange
	 *     TerminalGroup.Group_1_0 returns CharacterRange
	 *     TerminalToken returns CharacterRange
	 *     TerminalTokenElement returns CharacterRange
	 *     ParenthesizedTerminalElement returns CharacterRange
	 *
	 * Constraint:
	 *     (left=CharacterRange_CharacterRange_1_0 right=Keyword (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_CharacterRange_TerminalToken(ISerializationContext context, CharacterRange semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AssignableTerminal returns CrossReference
	 *     ParenthesizedAssignableElement returns CrossReference
	 *     AssignableAlternatives returns CrossReference
	 *     AssignableAlternatives.Alternatives_1_0 returns CrossReference
	 *     CrossReference returns CrossReference
	 *
	 * Constraint:
	 *     (type=TypeRef terminal=CrossReferenceableTerminal?)
	 * </pre>
	 */
	protected void sequence_CrossReference(ISerializationContext context, CrossReference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EnumLiterals returns EnumLiteralDeclaration
	 *     EnumLiterals.Alternatives_1_0 returns EnumLiteralDeclaration
	 *     EnumLiteralDeclaration returns EnumLiteralDeclaration
	 *
	 * Constraint:
	 *     (enumLiteral=[EEnumLiteral|ID] literal=Keyword?)
	 * </pre>
	 */
	protected void sequence_EnumLiteralDeclaration(ISerializationContext context, EnumLiteralDeclaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     EnumLiterals returns Alternatives
	 *
	 * Constraint:
	 *     (groups+=EnumLiterals_Alternatives_1_0 groups+=EnumLiteralDeclaration+)
	 * </pre>
	 */
	protected void sequence_EnumLiterals(ISerializationContext context, Alternatives semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractRule returns EnumRule
	 *     EnumRule returns EnumRule
	 *
	 * Constraint:
	 *     (name=ID type=TypeRef? alternatives=EnumLiterals)
	 * </pre>
	 */
	protected void sequence_EnumRule(ISerializationContext context, EnumRule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractMetamodelDeclaration returns GeneratedMetamodel
	 *     GeneratedMetamodel returns GeneratedMetamodel
	 *
	 * Constraint:
	 *     (name=ID ePackage=[EPackage|STRING] alias=ID?)
	 * </pre>
	 */
	protected void sequence_GeneratedMetamodel(ISerializationContext context, GeneratedMetamodel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Grammar returns Grammar
	 *
	 * Constraint:
	 *     (
	 *         name=GrammarID 
	 *         (usedGrammars+=[Grammar|GrammarID] usedGrammars+=[Grammar|GrammarID]*)? 
	 *         (definesHiddenTokens?='hidden' (hiddenTokens+=[AbstractRule|ID] hiddenTokens+=[AbstractRule|ID]*)?)? 
	 *         metamodelDeclarations+=AbstractMetamodelDeclaration* 
	 *         rules+=AbstractRule+
	 *     )
	 * </pre>
	 */
	protected void sequence_Grammar(ISerializationContext context, Grammar semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Keyword returns Keyword
	 *     AssignableTerminal returns Keyword
	 *     ParenthesizedAssignableElement returns Keyword
	 *     AssignableAlternatives returns Keyword
	 *     AssignableAlternatives.Alternatives_1_0 returns Keyword
	 *     CrossReferenceableTerminal returns Keyword
	 *     CharacterRange returns Keyword
	 *     CharacterRange.CharacterRange_1_0 returns Keyword
	 *
	 * Constraint:
	 *     value=STRING
	 * </pre>
	 */
	protected void sequence_Keyword(ISerializationContext context, Keyword semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.KEYWORD__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.KEYWORD__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getKeywordAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns Keyword
	 *     TerminalAlternatives.Alternatives_1_0 returns Keyword
	 *     TerminalGroup returns Keyword
	 *     TerminalGroup.Group_1_0 returns Keyword
	 *     TerminalToken returns Keyword
	 *     TerminalTokenElement returns Keyword
	 *     ParenthesizedTerminalElement returns Keyword
	 *
	 * Constraint:
	 *     (value=STRING (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_Keyword_TerminalToken(ISerializationContext context, Keyword semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractNegatedToken returns NegatedToken
	 *     NegatedToken returns NegatedToken
	 *
	 * Constraint:
	 *     terminal=TerminalTokenElement
	 * </pre>
	 */
	protected void sequence_NegatedToken(ISerializationContext context, NegatedToken semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.ABSTRACT_NEGATED_TOKEN__TERMINAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.ABSTRACT_NEGATED_TOKEN__TERMINAL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNegatedTokenAccess().getTerminalTerminalTokenElementParserRuleCall_1_0(), semanticObject.getTerminal());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns NegatedToken
	 *     TerminalAlternatives.Alternatives_1_0 returns NegatedToken
	 *     TerminalGroup returns NegatedToken
	 *     TerminalGroup.Group_1_0 returns NegatedToken
	 *     TerminalToken returns NegatedToken
	 *     TerminalTokenElement returns NegatedToken
	 *     ParenthesizedTerminalElement returns NegatedToken
	 *
	 * Constraint:
	 *     (terminal=TerminalTokenElement (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_NegatedToken_TerminalToken(ISerializationContext context, NegatedToken semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractRule returns ParserRule
	 *     ParserRule returns ParserRule
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         type=TypeRef? 
	 *         (definesHiddenTokens?='hidden' (hiddenTokens+=[AbstractRule|ID] hiddenTokens+=[AbstractRule|ID]*)?)? 
	 *         alternatives=Alternatives
	 *     )
	 * </pre>
	 */
	protected void sequence_ParserRule(ISerializationContext context, org.eclipse.xtext.parser.terminalrules.xtextTerminalsTestLanguage.ParserRule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractMetamodelDeclaration returns ReferencedMetamodel
	 *     ReferencedMetamodel returns ReferencedMetamodel
	 *
	 * Constraint:
	 *     (ePackage=[EPackage|STRING] alias=ID?)
	 * </pre>
	 */
	protected void sequence_ReferencedMetamodel(ISerializationContext context, ReferencedMetamodel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     RuleCall returns RuleCall
	 *     AssignableTerminal returns RuleCall
	 *     ParenthesizedAssignableElement returns RuleCall
	 *     AssignableAlternatives returns RuleCall
	 *     AssignableAlternatives.Alternatives_1_0 returns RuleCall
	 *     CrossReferenceableTerminal returns RuleCall
	 *
	 * Constraint:
	 *     rule=[AbstractRule|ID]
	 * </pre>
	 */
	protected void sequence_RuleCall(ISerializationContext context, RuleCall semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.RULE_CALL__RULE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.RULE_CALL__RULE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getRuleCallAccess().getRuleAbstractRuleIDTerminalRuleCall_0_1(), semanticObject.eGet(XtextTerminalsTestLanguagePackage.Literals.RULE_CALL__RULE, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns RuleCall
	 *     TerminalAlternatives.Alternatives_1_0 returns RuleCall
	 *     TerminalGroup returns RuleCall
	 *     TerminalGroup.Group_1_0 returns RuleCall
	 *     TerminalToken returns RuleCall
	 *     TerminalTokenElement returns RuleCall
	 *     ParenthesizedTerminalElement returns RuleCall
	 *
	 * Constraint:
	 *     (rule=[AbstractRule|ID] (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_RuleCall_TerminalToken(ISerializationContext context, RuleCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns Alternatives
	 *     TerminalAlternatives.Alternatives_1_0 returns Alternatives
	 *     TerminalGroup returns Alternatives
	 *     TerminalGroup.Group_1_0 returns Alternatives
	 *     TerminalToken returns Alternatives
	 *     TerminalTokenElement returns Alternatives
	 *     ParenthesizedTerminalElement returns Alternatives
	 *
	 * Constraint:
	 *     (groups+=TerminalAlternatives_Alternatives_1_0 groups+=TerminalGroup+ (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_TerminalAlternatives_TerminalToken(ISerializationContext context, Alternatives semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns Group
	 *     TerminalAlternatives.Alternatives_1_0 returns Group
	 *     TerminalGroup returns Group
	 *     TerminalGroup.Group_1_0 returns Group
	 *     TerminalToken returns Group
	 *     TerminalTokenElement returns Group
	 *     ParenthesizedTerminalElement returns Group
	 *
	 * Constraint:
	 *     (tokens+=TerminalGroup_Group_1_0 tokens+=TerminalToken+ (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_TerminalGroup_TerminalToken(ISerializationContext context, Group semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractRule returns TerminalRule
	 *     TerminalRule returns TerminalRule
	 *
	 * Constraint:
	 *     (name=ID type=TypeRef? alternatives=TerminalAlternatives)
	 * </pre>
	 */
	protected void sequence_TerminalRule(ISerializationContext context, TerminalRule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns UntilToken
	 *     TerminalAlternatives.Alternatives_1_0 returns UntilToken
	 *     TerminalGroup returns UntilToken
	 *     TerminalGroup.Group_1_0 returns UntilToken
	 *     TerminalToken returns UntilToken
	 *     TerminalTokenElement returns UntilToken
	 *     ParenthesizedTerminalElement returns UntilToken
	 *
	 * Constraint:
	 *     (terminal=TerminalTokenElement (cardinality='?' | cardinality='*' | cardinality='+')*)
	 * </pre>
	 */
	protected void sequence_TerminalToken_UntilToken(ISerializationContext context, UntilToken semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TerminalAlternatives returns Wildcard
	 *     TerminalAlternatives.Alternatives_1_0 returns Wildcard
	 *     TerminalGroup returns Wildcard
	 *     TerminalGroup.Group_1_0 returns Wildcard
	 *     TerminalToken returns Wildcard
	 *     TerminalTokenElement returns Wildcard
	 *     ParenthesizedTerminalElement returns Wildcard
	 *
	 * Constraint:
	 *     (cardinality='?' | cardinality='*' | cardinality='+')*
	 * </pre>
	 */
	protected void sequence_TerminalToken_Wildcard(ISerializationContext context, Wildcard semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TypeRef returns TypeRef
	 *
	 * Constraint:
	 *     (metamodel=[AbstractMetamodelDeclaration|ID]? classifier=[EClassifier|ID])
	 * </pre>
	 */
	protected void sequence_TypeRef(ISerializationContext context, TypeRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AbstractNegatedToken returns UntilToken
	 *     UntilToken returns UntilToken
	 *
	 * Constraint:
	 *     terminal=TerminalTokenElement
	 * </pre>
	 */
	protected void sequence_UntilToken(ISerializationContext context, UntilToken semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextTerminalsTestLanguagePackage.Literals.ABSTRACT_NEGATED_TOKEN__TERMINAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextTerminalsTestLanguagePackage.Literals.ABSTRACT_NEGATED_TOKEN__TERMINAL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUntilTokenAccess().getTerminalTerminalTokenElementParserRuleCall_1_0(), semanticObject.getTerminal());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Wildcard returns Wildcard
	 *
	 * Constraint:
	 *     {Wildcard}
	 * </pre>
	 */
	protected void sequence_Wildcard(ISerializationContext context, Wildcard semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
