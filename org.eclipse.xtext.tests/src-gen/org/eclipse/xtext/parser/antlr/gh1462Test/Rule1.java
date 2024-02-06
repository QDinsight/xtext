/**
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.parser.antlr.gh1462Test;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getLeft <em>Left</em>}</li>
 *   <li>{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getRight <em>Right</em>}</li>
 *   <li>{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getUnit <em>Unit</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.parser.antlr.gh1462Test.Gh1462TestPackage#getRule1()
 * @model
 * @generated
 */
public interface Rule1 extends EObject
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' attribute.
   * @see #setLeft(int)
   * @see org.eclipse.xtext.parser.antlr.gh1462Test.Gh1462TestPackage#getRule1_Left()
   * @model
   * @generated
   */
  int getLeft();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getLeft <em>Left</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' attribute.
   * @see #getLeft()
   * @generated
   */
  void setLeft(int value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' attribute.
   * @see #setRight(int)
   * @see org.eclipse.xtext.parser.antlr.gh1462Test.Gh1462TestPackage#getRule1_Right()
   * @model
   * @generated
   */
  int getRight();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getRight <em>Right</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' attribute.
   * @see #getRight()
   * @generated
   */
  void setRight(int value);

  /**
   * Returns the value of the '<em><b>Unit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unit</em>' attribute.
   * @see #setUnit(String)
   * @see org.eclipse.xtext.parser.antlr.gh1462Test.Gh1462TestPackage#getRule1_Unit()
   * @model
   * @generated
   */
  String getUnit();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parser.antlr.gh1462Test.Rule1#getUnit <em>Unit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unit</em>' attribute.
   * @see #getUnit()
   * @generated
   */
  void setUnit(String value);

} // Rule1
