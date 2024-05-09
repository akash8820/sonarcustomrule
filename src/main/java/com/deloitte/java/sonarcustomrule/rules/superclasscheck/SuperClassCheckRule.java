package com.deloitte.java.sonarcustomrule.rules.superclasscheck;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.Tree;

public abstract class SuperClassCheckRule extends IssuableSubscriptionVisitor {

	protected abstract List<String> getSuperClassNames();

	protected abstract String getMessage();

	private Set<String> foundSuperClasses = new HashSet<>();

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
			if (!superClass.isEmpty()) {
				foundSuperClasses.add(superClass);
			}
		}
	}

	@Override
	public void leaveFile(JavaFileScannerContext context) {
		List<Tree> classTrees = context.getTree().types();
		for (Tree classTree : classTrees) {
			if (classTree.is(Tree.Kind.CLASS)) {
				ClassTree classDecl = (ClassTree) classTree;
				List<String> superClassNames = getSuperClassNames();
				for (String superClass : superClassNames) {
					if (!foundSuperClasses.contains(superClass)) {
						reportIssue(classDecl.simpleName(), getMessage() + ": " + superClass);
						break; // Raise issue only once if no instance is found
					}
				}
			}
		}
		foundSuperClasses.clear();
	}
}
