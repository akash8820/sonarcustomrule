package com.deloitte.java.sonarcustomrule.rules.sonarcheck;

import java.util.Collections;
import java.util.List;
import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeTree;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategy;

public abstract class SonarCustomCheckRule extends IssuableSubscriptionVisitor implements JavaFileScanner {

	@RuleProperty(description = "Name of the annotation")
	protected String name;

	private boolean annotationPresent;
	private boolean returnTypePresent;
	private AnnotationCheckStrategy annotationStrategy;

	public SonarCustomCheckRule(AnnotationCheckStrategy strategy) {
		this.annotationStrategy = strategy;
	}

	@Override
	public List<Kind> nodesToVisit() {
		return Collections.singletonList(Tree.Kind.METHOD);
	}

	@Override
	public void visitNode(Tree tree) {
		MethodTree methodTree = (MethodTree) tree;
		annotationPresent = annotationStrategy.checkAnnotation(methodTree, getAnnotationNames()) ? true
				: annotationPresent;
		returnTypePresent = annotationStrategy.checkReturnTypeAndAnnotatedWithBean(methodTree, getReturnTypeNames())
				? true
				: returnTypePresent;
	}

	@Override
	public void leaveFile(JavaFileScannerContext context) {
		if (!annotationPresent && !returnTypePresent) {
			reportIssue(context.getTree(), getMessage());
		}
		annotationPresent = false;
		returnTypePresent = false;
	}

	protected abstract List<String> getAnnotationNames();

	protected abstract List<String> getReturnTypeNames();

	protected abstract String getMessage();

}
