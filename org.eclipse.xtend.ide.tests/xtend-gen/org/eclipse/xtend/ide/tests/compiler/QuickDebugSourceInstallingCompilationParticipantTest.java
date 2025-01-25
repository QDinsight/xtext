/**
 * Copyright (c) 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.tests.compiler;

import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.eclipse.core.resources.IFile;
import org.eclipse.xtend.ide.tests.AbstractXtendUITestCase;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Christian Dietrich - Initial contribution and API
 */
@SuppressWarnings("all")
public class QuickDebugSourceInstallingCompilationParticipantTest extends AbstractXtendUITestCase {
  @Inject
  private WorkbenchTestHelper workbenchTestHelper;

  @Test
  public void testIfThereIsAnyStatum() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package hello;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public class Hello {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.workbenchTestHelper.createFile("hello/Hello.java", _builder.toString());
      IResourcesSetupUtil.waitForBuild();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package somePackage");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class Outer {");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("def dosomething() {");
      _builder_1.newLine();
      _builder_1.append("  \t");
      _builder_1.append("println(1)");
      _builder_1.newLine();
      _builder_1.append("  \t");
      _builder_1.append("println(2)");
      _builder_1.newLine();
      _builder_1.append("  \t");
      _builder_1.append("println(3)");
      _builder_1.newLine();
      _builder_1.append("  ");
      _builder_1.append("}");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      final IFile source = this.workbenchTestHelper.createFile("somePackage/Outer.xtend", _builder_1.toString());
      IResourcesSetupUtil.waitForBuild();
      final IFile clazz = source.getProject().getFile("bin/somePackage/Outer.class");
      Assert.assertTrue("bytecode not found", clazz.exists());
      final AtomicBoolean debugInfoFound = new AtomicBoolean(false);
      try (final InputStream in = new Function0<InputStream>() {
        @Override
        public InputStream apply() {
          try {
            return clazz.getContents();
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      }.apply()) {
        final byte[] bytes = ByteStreams.toByteArray(in);
        final ClassReader r = new ClassReader(bytes);
        r.accept(new ClassVisitor(Opcodes.ASM9) {
          @Override
          public void visitSource(final String source, final String debug) {
            boolean _equals = Objects.equals("Outer.java", source);
            if (_equals) {
              StringConcatenation _builder = new StringConcatenation();
              _builder.append("SMAP");
              _builder.newLine();
              _builder.append("Outer.java");
              _builder.newLine();
              _builder.append("Xtend");
              _builder.newLine();
              _builder.append("*S Xtend");
              _builder.newLine();
              _builder.append("*F");
              _builder.newLine();
              _builder.append("+ 0 Outer.xtend");
              _builder.newLine();
              _builder.append("somePackage/Outer.xtend");
              _builder.newLine();
              _builder.append("*L");
              _builder.newLine();
              _builder.append("4:8,2");
              _builder.newLine();
              _builder.append("5:10");
              _builder.newLine();
              _builder.append("6:11");
              _builder.newLine();
              _builder.append("7:12");
              _builder.newLine();
              _builder.append("4:13,2");
              _builder.newLine();
              _builder.append("*E");
              _builder.newLine();
              Assert.assertEquals(_builder.toString().replace("\r", ""), 
                debug.replace("\r", ""));
              debugInfoFound.set(true);
            }
            super.visitSource(source, debug);
          }
        }, 0);
        boolean _get = debugInfoFound.get();
        boolean _not = (!_get);
        if (_not) {
          Assert.fail("No source attribute found in bytecode");
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
