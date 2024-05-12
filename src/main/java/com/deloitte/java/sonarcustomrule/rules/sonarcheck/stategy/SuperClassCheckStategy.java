package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.tree.Tree;

public interface SuperClassCheckStategy {
	
	public boolean checkSuperClass(Tree tree, List<String> superClassNamesToCheck);
}
