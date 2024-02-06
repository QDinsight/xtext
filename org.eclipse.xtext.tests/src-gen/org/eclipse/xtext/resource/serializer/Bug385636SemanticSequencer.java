/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.resource.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.resource.bug385636.Bug385636Package;
import org.eclipse.xtext.resource.bug385636.DefineVariable;
import org.eclipse.xtext.resource.bug385636.DefineVariables;
import org.eclipse.xtext.resource.bug385636.Expression_Equal;
import org.eclipse.xtext.resource.bug385636.Expression_Larger_Equal;
import org.eclipse.xtext.resource.bug385636.Expression_Not_Equal;
import org.eclipse.xtext.resource.bug385636.Expression_Not_Greater;
import org.eclipse.xtext.resource.bug385636.Expression_Not_Less;
import org.eclipse.xtext.resource.bug385636.Expression_Smaller;
import org.eclipse.xtext.resource.bug385636.Expression_Smaller_Equal;
import org.eclipse.xtext.resource.bug385636.Expression_VariableName;
import org.eclipse.xtext.resource.bug385636.NVariableAccess;
import org.eclipse.xtext.resource.bug385636.Program;
import org.eclipse.xtext.resource.services.Bug385636GrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class Bug385636SemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private Bug385636GrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == Bug385636Package.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case Bug385636Package.DEFINE_VARIABLE:
				sequence_DefineVariable(context, (DefineVariable) semanticObject); 
				return; 
			case Bug385636Package.DEFINE_VARIABLES:
				sequence_DefineVariables(context, (DefineVariables) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_EQUAL:
				sequence_Statement(context, (Expression_Equal) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_LARGER_EQUAL:
				sequence_Statement(context, (Expression_Larger_Equal) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_NOT_EQUAL:
				sequence_Statement(context, (Expression_Not_Equal) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_NOT_GREATER:
				sequence_Statement(context, (Expression_Not_Greater) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_NOT_LESS:
				sequence_Statement(context, (Expression_Not_Less) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_SMALLER:
				sequence_Statement(context, (Expression_Smaller) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_SMALLER_EQUAL:
				sequence_Statement(context, (Expression_Smaller_Equal) semanticObject); 
				return; 
			case Bug385636Package.EXPRESSION_VARIABLE_NAME:
				sequence_Expression_VariableName(context, (Expression_VariableName) semanticObject); 
				return; 
			case Bug385636Package.NVARIABLE_ACCESS:
				sequence_NVariableAccess(context, (NVariableAccess) semanticObject); 
				return; 
			case Bug385636Package.PROGRAM:
				sequence_Program(context, (Program) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     DefineVariable returns DefineVariable
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_DefineVariable(ISerializationContext context, DefineVariable semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.DEFINE_VARIABLE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.DEFINE_VARIABLE__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getDefineVariableAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     DefineVariables returns DefineVariables
	 *
	 * Constraint:
	 *     variables+=DefineVariable+
	 * </pre>
	 */
	protected void sequence_DefineVariables(ISerializationContext context, DefineVariables semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_VariableName
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_VariableName
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_VariableName
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_VariableName
	 *     Statement.Expression_Equal_1_3_1 returns Expression_VariableName
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_VariableName
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_VariableName
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_VariableName
	 *     Expression_VariableName returns Expression_VariableName
	 *
	 * Constraint:
	 *     variable=NVariableAccess
	 * </pre>
	 */
	protected void sequence_Expression_VariableName(ISerializationContext context, Expression_VariableName semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_VARIABLE_NAME__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_VARIABLE_NAME__VARIABLE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getExpression_VariableNameAccess().getVariableNVariableAccessParserRuleCall_0(), semanticObject.getVariable());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     NVariableAccess returns NVariableAccess
	 *
	 * Constraint:
	 *     variable=[DefineVariable|ID]
	 * </pre>
	 */
	protected void sequence_NVariableAccess(ISerializationContext context, NVariableAccess semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.NVARIABLE_ACCESS__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.NVARIABLE_ACCESS__VARIABLE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNVariableAccessAccess().getVariableDefineVariableIDTerminalRuleCall_0_1(), semanticObject.eGet(Bug385636Package.Literals.NVARIABLE_ACCESS__VARIABLE, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Program returns Program
	 *
	 * Constraint:
	 *     (define=DefineVariables statements+=Statement*)
	 * </pre>
	 */
	protected void sequence_Program(ISerializationContext context, Program semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Equal
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Equal
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Equal
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Equal
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Equal
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Equal
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Equal
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Equal
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Equal_1_3_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Equal semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_EQUAL__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_EQUAL__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_EQUAL__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_EQUAL__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_EqualLeftAction_1_3_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_3_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Larger_Equal
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Larger_Equal
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Larger_Equal
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Larger_Equal_1_0_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Larger_Equal semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_LARGER_EQUAL__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_LARGER_EQUAL__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_LARGER_EQUAL__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_LARGER_EQUAL__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_Larger_EqualLeftAction_1_0_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_0_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Not_Equal
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Not_Equal
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Not_Equal
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Not_Equal
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Not_Equal
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Not_Equal
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Not_Equal
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Not_Equal
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Not_Equal_1_4_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Not_Equal semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_EQUAL__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_EQUAL__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_EQUAL__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_EQUAL__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_Not_EqualLeftAction_1_4_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_4_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Not_Greater
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Not_Greater
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Not_Greater
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Not_Greater
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Not_Greater
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Not_Greater
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Not_Greater
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Not_Greater
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Not_Greater_1_6_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Not_Greater semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_GREATER__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_GREATER__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_GREATER__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_GREATER__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_Not_GreaterLeftAction_1_6_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_6_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Not_Less
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Not_Less
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Not_Less
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Not_Less
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Not_Less
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Not_Less
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Not_Less
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Not_Less
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Not_Less_1_5_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Not_Less semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_LESS__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_LESS__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_LESS__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_NOT_LESS__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_Not_LessLeftAction_1_5_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_5_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Smaller
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Smaller
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Smaller
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Smaller
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Smaller
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Smaller
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Smaller
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Smaller
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Smaller_1_1_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Smaller semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_SmallerLeftAction_1_1_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Statement returns Expression_Smaller_Equal
	 *     Statement.Expression_Larger_Equal_1_0_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Smaller_1_1_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Smaller_Equal_1_2_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Equal_1_3_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Not_Equal_1_4_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Not_Less_1_5_1 returns Expression_Smaller_Equal
	 *     Statement.Expression_Not_Greater_1_6_1 returns Expression_Smaller_Equal
	 *
	 * Constraint:
	 *     (left=Statement_Expression_Smaller_Equal_1_2_1 right=Expression_VariableName)
	 * </pre>
	 */
	protected void sequence_Statement(ISerializationContext context, Expression_Smaller_Equal semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER_EQUAL__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER_EQUAL__LEFT));
			if (transientValues.isValueTransient(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER_EQUAL__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, Bug385636Package.Literals.EXPRESSION_SMALLER_EQUAL__RIGHT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getStatementAccess().getExpression_Smaller_EqualLeftAction_1_2_1(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getStatementAccess().getRightExpression_VariableNameParserRuleCall_1_2_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
}
