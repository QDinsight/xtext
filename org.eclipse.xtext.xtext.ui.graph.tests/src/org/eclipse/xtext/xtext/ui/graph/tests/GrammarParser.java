/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.xtext.ui.graph.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;

public class GrammarParser {

	public Grammar parse(String rules) throws IOException {
		XtextResourceSet resourceSet = new XtextResourceSet();
		resourceSet.setClasspathURIContext(GrammarParser.class);
		Resource grammarResource = resourceSet.createResource(URI.createURI("Test.xtext"));
		grammarResource.load(new StringInputStream(
				"grammar Test with org.eclipse.xtext.common.Terminals \n"
				+ "generate test \"Test\"\n" + rules), null);
		EList<EObject> contents = grammarResource.getContents();
		assertEquals(1, contents.size());
		EObject root = contents.get(0);
		assertTrue(root instanceof Grammar);
		return (Grammar) root;
	}

}
