package com.deloitte.java.sonarcustomrule.rules.superclasscheck;

import java.util.Collections;
import java.util.List;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.Tree;

public abstract class SuperClassCheckRule extends IssuableSubscriptionVisitor {

	protected abstract List<String> getSuperClassNames();

	protected abstract String getMessage();

	@Override
	public List<Tree.Kind> nodesToVisit() {
		return Collections.singletonList(Tree.Kind.CLASS);
	}

	@Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;
        Tree superClassTree = classTree.superClass();
        if (superClassTree != null && superClassTree.is(Tree.Kind.IDENTIFIER)) {
            IdentifierTree identifierTree = (IdentifierTree) superClassTree;
            String superClass = identifierTree.name();
            List<String> superClassNames = getSuperClassNames();
            if (!superClass.isEmpty() && !superClassNames.contains(superClass)) {
                reportIssue(superClassTree, getMessage() + " " + superClass);
            }
        }
    }
}
