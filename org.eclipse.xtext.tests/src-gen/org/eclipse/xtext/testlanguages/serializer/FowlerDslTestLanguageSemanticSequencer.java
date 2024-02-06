/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.testlanguages.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.testlanguages.fowlerdsl.Command;
import org.eclipse.xtext.testlanguages.fowlerdsl.Event;
import org.eclipse.xtext.testlanguages.fowlerdsl.FowlerdslPackage;
import org.eclipse.xtext.testlanguages.fowlerdsl.State;
import org.eclipse.xtext.testlanguages.fowlerdsl.Statemachine;
import org.eclipse.xtext.testlanguages.fowlerdsl.Transition;
import org.eclipse.xtext.testlanguages.services.FowlerDslTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class FowlerDslTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private FowlerDslTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == FowlerdslPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case FowlerdslPackage.COMMAND:
				sequence_Command(context, (Command) semanticObject); 
				return; 
			case FowlerdslPackage.EVENT:
				sequence_Event(context, (Event) semanticObject); 
				return; 
			case FowlerdslPackage.STATE:
				sequence_State(context, (State) semanticObject); 
				return; 
			case FowlerdslPackage.STATEMACHINE:
				sequence_Statemachine(context, (Statemachine) semanticObject); 
				return; 
			case FowlerdslPackage.TRANSITION:
				sequence_Transition(context, (Transition) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Command returns Command
	 *
	 * Constraint:
	 *     (name=ID code=ID)
	 * </pre>
	 */
	protected void sequence_Command(ISerializationContext context, Command semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FowlerdslPackage.Literals.COMMAND__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FowlerdslPackage.Literals.COMMAND__NAME));
			if (transientValues.isValueTransient(semanticObject, FowlerdslPackage.Literals.COMMAND__CODE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FowlerdslPackage.Literals.COMMAND__CODE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getCommandAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getCommandAccess().getCodeIDTerminalRuleCall_1_0(), semanticObject.getCode());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Event returns Event
	 *
	 * Constraint:
	 *     (resetting?='resetting'? name=ID code=ID)
	 * </pre>
	 */
	protected void sequence_Event(ISerializationContext context, Event semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     State returns State
	 *
	 * Constraint:
	 *     (name=ID actions+=[Command|ID]* transitions+=Transition*)
	 * </pre>
	 */
	protected void sequence_State(ISerializationContext context, State semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statemachine returns Statemachine
	 *
	 * Constraint:
	 *     (events+=Event* commands+=Command* states+=State*)
	 * </pre>
	 */
	protected void sequence_Statemachine(ISerializationContext context, Statemachine semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Transition returns Transition
	 *
	 * Constraint:
	 *     (event=[Event|ID] state=[State|ID])
	 * </pre>
	 */
	protected void sequence_Transition(ISerializationContext context, Transition semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FowlerdslPackage.Literals.TRANSITION__EVENT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FowlerdslPackage.Literals.TRANSITION__EVENT));
			if (transientValues.isValueTransient(semanticObject, FowlerdslPackage.Literals.TRANSITION__STATE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FowlerdslPackage.Literals.TRANSITION__STATE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getTransitionAccess().getEventEventIDTerminalRuleCall_0_0_1(), semanticObject.eGet(FowlerdslPackage.Literals.TRANSITION__EVENT, false));
		feeder.accept(grammarAccess.getTransitionAccess().getStateStateIDTerminalRuleCall_2_0_1(), semanticObject.eGet(FowlerdslPackage.Literals.TRANSITION__STATE, false));
		feeder.finish();
	}
	
	
}
