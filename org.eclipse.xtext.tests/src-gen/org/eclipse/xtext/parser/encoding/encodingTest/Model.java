/**
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.parser.encoding.encodingTest;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.parser.encoding.encodingTest.Model#getWords <em>Words</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.parser.encoding.encodingTest.EncodingTestPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Words</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.parser.encoding.encodingTest.Word}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Words</em>' containment reference list.
   * @see org.eclipse.xtext.parser.encoding.encodingTest.EncodingTestPackage#getModel_Words()
   * @model containment="true"
   * @generated
   */
  EList<Word> getWords();

} // Model
