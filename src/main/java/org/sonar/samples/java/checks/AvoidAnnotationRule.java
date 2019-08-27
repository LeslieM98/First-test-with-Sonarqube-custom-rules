/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java.checks;

import java.util.List;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

@Rule(
        key = "Return parameter should not equal single param type.",
        name = "Return type and parameter of a method should not be the same",
        description = "For a method having a single parameter, the types of its return value and its parameter should never be the same.",
        tags = {"bug"})
public class AvoidAnnotationRule extends BaseTreeVisitor implements JavaFileScanner {

    private static final String DEFAULT_VALUE = "Inject";

    private JavaFileScannerContext context;

    /**
     * Name of the annotation to avoid. Value can be set by users in Quality profiles.
     * The key
     */
    @RuleProperty(
            defaultValue = DEFAULT_VALUE,
            description = "Return parameter should not equal single param type.")
    protected String name;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;

        scan(context.getTree());

        System.out.println(PrinterVisitor.print(context.getTree()));
    }


    @Override
    public void visitMethod(MethodTree tree) {
        tree.symbol().name();
        super.visitMethod(tree);
    }
}
