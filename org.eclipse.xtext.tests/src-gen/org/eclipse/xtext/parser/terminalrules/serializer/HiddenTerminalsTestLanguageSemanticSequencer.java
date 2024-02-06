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
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.DatatypeHiddens;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.HiddenTerminalsTestLanguagePackage;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.HidingHiddens;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.InheritingHiddens;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.InheritingHiddensCall;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.OverridingHiddens;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.OverridingHiddensCall;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.WithHiddens;
import org.eclipse.xtext.parser.terminalrules.hiddenTerminalsTestLanguage.WithoutHiddens;
import org.eclipse.xtext.parser.terminalrules.services.HiddenTerminalsTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class HiddenTerminalsTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private HiddenTerminalsTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == HiddenTerminalsTestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case HiddenTerminalsTestLanguagePackage.DATATYPE_HIDDENS:
				sequence_DatatypeHiddens(context, (DatatypeHiddens) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.HIDING_HIDDENS:
				sequence_HidingHiddens(context, (HidingHiddens) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.INHERITING_HIDDENS:
				sequence_InheritingHiddens(context, (InheritingHiddens) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.INHERITING_HIDDENS_CALL:
				sequence_InheritingHiddensCall(context, (InheritingHiddensCall) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.OVERRIDING_HIDDENS:
				sequence_OverridingHiddens(context, (OverridingHiddens) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.OVERRIDING_HIDDENS_CALL:
				sequence_OverridingHiddensCall(context, (OverridingHiddensCall) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.WITH_HIDDENS:
				sequence_WithHiddens(context, (WithHiddens) semanticObject); 
				return; 
			case HiddenTerminalsTestLanguagePackage.WITHOUT_HIDDENS:
				sequence_WithoutHiddens(context, (WithoutHiddens) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns DatatypeHiddens
	 *     DatatypeHiddens returns DatatypeHiddens
	 *
	 * Constraint:
	 *     valid?=DatatypeRule
	 * </pre>
	 */
	protected void sequence_DatatypeHiddens(ISerializationContext context, DatatypeHiddens semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getDatatypeHiddensAccess().getValidDatatypeRuleParserRuleCall_1_0(), semanticObject.isValid());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     HidingHiddens returns HidingHiddens
	 *
	 * Constraint:
	 *     (space=WS called=InheritingHiddensCall)
	 * </pre>
	 */
	protected void sequence_HidingHiddens(ISerializationContext context, HidingHiddens semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.HIDING_HIDDENS__SPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.HIDING_HIDDENS__SPACE));
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.HIDING_HIDDENS__CALLED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.HIDING_HIDDENS__CALLED));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getHidingHiddensAccess().getSpaceWSTerminalRuleCall_1_0(), semanticObject.getSpace());
		feeder.accept(grammarAccess.getHidingHiddensAccess().getCalledInheritingHiddensCallParserRuleCall_2_0(), semanticObject.getCalled());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     InheritingHiddensCall returns InheritingHiddensCall
	 *
	 * Constraint:
	 *     valid?=';'
	 * </pre>
	 */
	protected void sequence_InheritingHiddensCall(ISerializationContext context, InheritingHiddensCall semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.INHERITING_HIDDENS_CALL__VALID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.INHERITING_HIDDENS_CALL__VALID));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getInheritingHiddensCallAccess().getValidSemicolonKeyword_1_0(), semanticObject.isValid());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns InheritingHiddens
	 *     InheritingHiddens returns InheritingHiddens
	 *
	 * Constraint:
	 *     ((called=InheritingHiddensCall | hidingCalled=HidingHiddens) valid?=';')
	 * </pre>
	 */
	protected void sequence_InheritingHiddens(ISerializationContext context, InheritingHiddens semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     OverridingHiddensCall returns OverridingHiddensCall
	 *
	 * Constraint:
	 *     (spaces+=WS? valid?=';')
	 * </pre>
	 */
	protected void sequence_OverridingHiddensCall(ISerializationContext context, OverridingHiddensCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns OverridingHiddens
	 *     OverridingHiddens returns OverridingHiddens
	 *
	 * Constraint:
	 *     (called=OverridingHiddensCall valid?=';')
	 * </pre>
	 */
	protected void sequence_OverridingHiddens(ISerializationContext context, OverridingHiddens semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.OVERRIDING_HIDDENS__CALLED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.OVERRIDING_HIDDENS__CALLED));
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getOverridingHiddensAccess().getCalledOverridingHiddensCallParserRuleCall_3_0(), semanticObject.getCalled());
		feeder.accept(grammarAccess.getOverridingHiddensAccess().getValidSemicolonKeyword_5_0(), semanticObject.isValid());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns WithHiddens
	 *     WithHiddens returns WithHiddens
	 *
	 * Constraint:
	 *     valid?=';'
	 * </pre>
	 */
	protected void sequence_WithHiddens(ISerializationContext context, WithHiddens semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HiddenTerminalsTestLanguagePackage.Literals.MODEL__VALID));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWithHiddensAccess().getValidSemicolonKeyword_2_0(), semanticObject.isValid());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns WithoutHiddens
	 *     WithoutHiddens returns WithoutHiddens
	 *
	 * Constraint:
	 *     (spaces+=WS spaces+=WS? valid?=';')
	 * </pre>
	 */
	protected void sequence_WithoutHiddens(ISerializationContext context, WithoutHiddens semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
