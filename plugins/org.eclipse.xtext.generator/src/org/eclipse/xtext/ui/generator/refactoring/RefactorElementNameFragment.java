/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.ui.generator.refactoring;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.AbstractGeneratorFragment;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class RefactorElementNameFragment extends AbstractGeneratorFragment {

	private boolean useJdtRefactoring = false;

	public void setUseJdtRefactoring(boolean useInferredJvmModel) {
		this.useJdtRefactoring = useInferredJvmModel;
	}

	@Override
	public Set<Binding> getGuiceBindingsUi(Grammar grammar) {
		BindFactory bindFactory = new BindFactory();
		bindFactory
			.addTypeToType("org.eclipse.xtext.ui.refactoring.IReferenceUpdater", 
					"org.eclipse.xtext.ui.refactoring.impl.DefaultReferenceUpdater");
		if (!useJdtRefactoring)
			return bindFactory
					.addTypeToType("org.eclipse.xtext.ui.refactoring.IRenameRefactoringProvider",
							"org.eclipse.xtext.ui.refactoring.impl.DefaultRenameRefactoringProvider")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.ui.IRenameSupport.Factory",
							"org.eclipse.xtext.ui.refactoring.ui.DefaultRenameSupport.Factory")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.ui.IRenameElementHandler",
							"org.eclipse.xtext.ui.refactoring.ui.DefaultRenameElementHandler")
					.getBindings();
		else
			return bindFactory
					.addTypeToType("org.eclipse.xtext.ui.refactoring.ui.IRenameElementHandler",
							"org.eclipse.xtext.common.types.ui.refactoring.JvmRenameElementHandler")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.IRenameRefactoringProvider",
							"org.eclipse.xtext.common.types.ui.refactoring.JvmRenameRefactoringProvider")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.IRenameProcessorAdapter.Factory",
							"org.eclipse.xtext.common.types.ui.refactoring.JavaRenameProcessorAdapter.Factory")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.ui.IRenameSupport.Factory",
							"org.eclipse.xtext.common.types.ui.refactoring.JdtAwareRenameSupportFactory")
					.addTypeToType("org.eclipse.xtext.ui.refactoring.IRenameStrategy.Provider",
							"org.eclipse.xtext.common.types.ui.refactoring.participant.JvmMemberRenameStrategy.Provider")
					.getBindings();
	}
}
