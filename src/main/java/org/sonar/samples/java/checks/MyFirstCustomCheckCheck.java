package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.samples.java.Data;

import java.util.List;

@Rule(key = "MyFirstCustomRule")
public class MyFirstCustomCheckCheck extends IssuableSubscriptionVisitor {

    Data d = new Data();
    @Override
    public List<Kind> nodesToVisit() {
        return List.of(Kind.METHOD, Kind.IMPORT, Kind.PACKAGE);
    }

    @Override
    public void visitNode(Tree tree) {
        switch (tree.kind())
        {
            case METHOD:
                MethodTree m = (MethodTree) tree;
                d.methodNames.put(((MethodTree) tree).simpleName().name(), tree.firstToken().line());
        }
    }
}