/**
 * Copyright (c) 2016 TypeFox GmbH (http://www.typefox.io) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.ide.tests.testlanguage;

import org.eclipse.xtext.ide.tests.testlanguage.PartialContentAssistTestLanguageStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class PartialContentAssistTestLanguageStandaloneSetup extends PartialContentAssistTestLanguageStandaloneSetupGenerated {
  public static void doSetup() {
    PartialContentAssistTestLanguageStandaloneSetup _partialContentAssistTestLanguageStandaloneSetup = new PartialContentAssistTestLanguageStandaloneSetup();
    _partialContentAssistTestLanguageStandaloneSetup.createInjectorAndDoEMFRegistration();
  }
}
