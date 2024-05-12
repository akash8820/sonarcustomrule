package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.Tree;

public class SuperClassCheckStategyImpl implements SuperClassCheckStategy {

	@Override
	public boolean checkSuperClass(Tree tree, List<String> superClassNamesToCheck) {
		ClassTree classTree = (ClassTree) tree;
		Tree superClassTree = classTree.superClass();
		if (superClassTree != null && superClassTree.is(Tree.Kind.IDENTIFIER)) {
			IdentifierTree identifierTree = (IdentifierTree) superClassTree;
			String superClass = identifierTree.name();
			if (superClassNamesToCheck != null && superClassNamesToCheck.contains(superClass))
				return Boolean.TRUE;

		}
		return Boolean.FALSE;

	}
}
