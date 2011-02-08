/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.xtext.ecoreInference;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.XtextSwitch;

import com.google.common.base.Function;
import com.google.common.collect.Sets;

/**
 * @author Heiko Behrens - Initial contribution and API
 * @author Sebastian Zarnekow
 *
 * @see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#metamodelInference
 */
public class Xtext2EcoreInterpretationContext {

	private final EClassifierInfos eClassifierInfos;

	private final Function<AbstractElement, EClassifier> classifierCalculator;

	private final Collection<EClassifierInfo> currentTypes = Sets.newLinkedHashSet();

	boolean isRuleCallAllowed = true;

	private Xtext2EcoreInterpretationContext(EClassifierInfos classifierInfos) {
		super();
		if (classifierInfos == null)
			throw new NullPointerException("classifierInfos may not be null");
		this.eClassifierInfos = classifierInfos;
		this.classifierCalculator = new ElementTypeCalculator(this.eClassifierInfos);
	}

	public Xtext2EcoreInterpretationContext(EClassifierInfos eClassifierInfos, EClassifierInfo currentType) {
		this(eClassifierInfos);
		currentTypes.add(currentType);
	}

	private Xtext2EcoreInterpretationContext(Collection<EClassifierInfo> currentTypes,
			EClassifierInfos classifierInfos, boolean isRuleCallAllowed) {
		this(classifierInfos);
		this.currentTypes.addAll(currentTypes);
		this.isRuleCallAllowed = isRuleCallAllowed;
	}

	public Xtext2EcoreInterpretationContext(EClassifierInfo newType, EClassifierInfos classifierInfos,
			boolean isRuleCallAllowed) {
		this(classifierInfos);
		this.currentTypes.add(newType);
		this.isRuleCallAllowed = isRuleCallAllowed;
	}

	public void addFeature(Assignment assignment) throws TransformationException {
		final String featureName = assignment.getFeature();
		boolean isMultivalue = GrammarUtil.isMultipleAssignment(assignment);
		boolean isContainment = true;
		EClassifierInfo featureTypeInfo;

		if (GrammarUtil.isBooleanAssignment(assignment)) {
			featureTypeInfo = getEClassifierInfoOrThrowException(EcorePackage.Literals.EBOOLEAN, assignment);
			isMultivalue = false;
		}
		else {
			AbstractElement terminal = assignment.getTerminal();
			if (terminal == null) {
				throw new TransformationException(TransformationErrorCode.NoSuchTypeAvailable, "Cannot derive type from incomplete assignment.", assignment);
			}
			EClassifier type = getTerminalType(terminal);
			isContainment = isContainmentAssignment(assignment);
			featureTypeInfo = getEClassifierInfoOrThrowException(type, assignment);
		}
		addFeature(featureName, featureTypeInfo, isMultivalue, isContainment, assignment);
	}

	public boolean isContainmentAssignment(Assignment assignment) {
		// TODO throw TransformationException in case of unexpected terminal
		return new XtextSwitch<Boolean>() {

			@Override
			public Boolean caseAlternatives(Alternatives object) {
				for (AbstractElement group: object.getElements())
					if (doSwitch(group))
						return true;
				return false;
			}

			@Override
			public Boolean caseCrossReference(CrossReference object) {
				return false;
			}

			@Override
			public Boolean caseAbstractElement(AbstractElement object) {
				return true;
			}

		}.doSwitch(assignment.getTerminal());
	}

	public void addFeature(String featureName, EClassifierInfo featureTypeInfo, boolean isMultivalue,
			boolean isContainment, AbstractElement parserElement) throws TransformationException {
		for (EClassifierInfo type : currentTypes)
			type.addFeature(featureName, featureTypeInfo, isMultivalue, isContainment, parserElement);
	}
	
	private EClassifier getTerminalType(AbstractElement terminal) throws TransformationException {
		final EClassifier result = classifierCalculator.apply(terminal);
		if (result == null) {
			final ICompositeNode node = NodeModelUtils.getNode(terminal);
			if (node != null) {
				throw new TransformationException(TransformationErrorCode.NoSuchTypeAvailable, "Cannot find type for '" + node.getText() + "'.", terminal);
			}
			throw new TransformationException(TransformationErrorCode.NoSuchTypeAvailable, "Cannot find type for " + terminal.eClass().getName(), terminal);
		}
		return result;
	}

	private EClassifierInfo getEClassifierInfoOrThrowException(EClassifier type, AbstractElement parserElement)
			throws TransformationException {
		final EClassifierInfo featureTypeInfo = eClassifierInfos.getInfoOrNull(type);
		if (featureTypeInfo == null) {
			throw new TransformationException(TransformationErrorCode.NoSuchTypeAvailable, "Cannot resolve type " + type.getName(),
					parserElement);
		}
		return featureTypeInfo;
	}

	public Xtext2EcoreInterpretationContext spawnContextForGroup() {
		Xtext2EcoreInterpretationContext result = new Xtext2EcoreInterpretationContext(currentTypes, eClassifierInfos,
				isRuleCallAllowed);
		return result;
	}

	public Xtext2EcoreInterpretationContext spawnContextWithCalledRule(EClassifierInfo newType, EObject parserElement)
			throws TransformationException {
		if (!isRuleCallAllowed)
			throw new TransformationException(TransformationErrorCode.MoreThanOneTypeChangeInOneRule,
					"Cannot change type twice within a rule", parserElement);

		return new Xtext2EcoreInterpretationContext(newType, eClassifierInfos, false);
	}

	public Xtext2EcoreInterpretationContext mergeSpawnedContexts(List<Xtext2EcoreInterpretationContext> contexts) {
		Xtext2EcoreInterpretationContext result = new Xtext2EcoreInterpretationContext(eClassifierInfos);
		// result's current types is union of all groups' types
		// result's isRuleCallAllowed is false if any group's value is false
		for (Xtext2EcoreInterpretationContext context : contexts) {
			result.currentTypes.addAll(context.currentTypes);
			result.isRuleCallAllowed &= context.isRuleCallAllowed;
		}
		return result;
	}

	public EClassifierInfo getCurrentCompatibleType() {
		return eClassifierInfos.getCompatibleTypeOf(currentTypes);
	}

	public Xtext2EcoreInterpretationContext spawnContextWithReferencedType(EClassifierInfo referencedType, EObject parserElement) {
		return new Xtext2EcoreInterpretationContext(referencedType, eClassifierInfos, false);
	}

}
