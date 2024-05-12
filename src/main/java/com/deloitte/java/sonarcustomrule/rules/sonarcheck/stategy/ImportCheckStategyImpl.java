package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.java.checks.helpers.ExpressionsHelper;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

public class ImportCheckStategyImpl implements ImportCheckStrategy {

	@Override
	public boolean checkImport(Tree tree, List<String> importNamesToCheck) {

		ImportTree importTree = (ImportTree) tree;
		String importName = ExpressionsHelper.concatenate(((ExpressionTree) importTree.qualifiedIdentifier()));
		if (null != importNamesToCheck && importNamesToCheck.contains(importName))
			return true;
		return false;

	}

	@Override
	public boolean checkReturnType(Tree tree, List<String> returnNamesToCheck) {

		MethodTree methodTree = (MethodTree) tree;
		MethodSymbol methodSymbol = methodTree.symbol();
		Type returnType = methodSymbol.returnType().type();
		if (returnNamesToCheck != null && returnNamesToCheck.contains(returnType.fullyQualifiedName())) {
			return true;
		}
		return false;

	}

}
