package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.tree.Tree;

public interface ImportCheckStrategy {

	boolean checkImport(Tree tree, List<String> importNamesToCheck);

	boolean checkReturnType(Tree tree,
			List<String> returnNamesToCheck);

}
