package com.deloitte.java.sonarcustomrule.rules;

import java.util.List;

import org.sonar.check.Rule;
import org.sonar.java.checks.helpers.ExpressionsHelper;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.Tree;

@Rule(key = "TimeLimiterImportMissing")
public class ImportPackageScan extends IssuableSubscriptionVisitor {

	private boolean hasSonarCheckRuleImport = false;
	private JavaFileScannerContext context;
	private boolean isAnnotationPresent = false;

	@Override
	public List<Tree.Kind> nodesToVisit() {
		return List.of(Tree.Kind.IMPORT);
	}

	@Override
	public void visitNode(Tree tree) {
		ImportTree importTree = (ImportTree) tree;
		String importName = ExpressionsHelper.concatenate(((ExpressionTree) importTree.qualifiedIdentifier()));
		System.out.println("Import: " + importName);
		if (importName.equals("io.github.resilience4j.timelimiter.annotation.TimeLimiter")) {
			hasSonarCheckRuleImport = true;
		}
	}

	@Override
	public void leaveFile(JavaFileScannerContext context) {
		if (!hasSonarCheckRuleImport) {
			context.reportIssue(this, context.getTree(),
					"Import io.github.resilience4j.timelimiter.annotation.TimeLimiter doesn't exist.");
		}
	}

}