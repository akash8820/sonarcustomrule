package com.deloitte.java.sonarcustomrule.rules.annotationcheck;

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

public abstract class AnnotationCheckRule extends IssuableSubscriptionVisitor implements JavaFileScanner {

	protected abstract List<String> getAnnotationNames();

	protected abstract List<String> getReturnTypeNames();

	protected abstract String getMessage();

	@RuleProperty(description = "Name of the annotation")
	protected String name;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		super.scanFile(context);
		boolean annotationPresent = false;
		boolean returnTypePresent = false;
		if (!annotationPresent && !returnTypePresent) {
			context.reportIssue(this, context.getTree(),
					getMessage() + " (None of the specified annotations or return types found)");
		}
	}

	@Override
	public List<Kind> nodesToVisit() {
		return Collections.singletonList(Tree.Kind.METHOD);
	}

	@Override
	public void visitNode(Tree tree) {
		MethodTree methodTree = (MethodTree) tree;
		boolean annotationPresent = checkAnnotation(methodTree);
		boolean returnTypePresent = checkReturnType(methodTree);
	}

	private boolean checkAnnotation(MethodTree methodTree) {
		List<AnnotationTree> annotations = methodTree.modifiers().annotations();
		List<String> annotationNamesToCheck = getAnnotationNames();
		for (AnnotationTree annotationTree : annotations) {
			TypeTree annotationType = annotationTree.annotationType();
			if (annotationType.is(Tree.Kind.IDENTIFIER)) {
				String annotationName = ((IdentifierTree) annotationType).name();
				if (annotationNamesToCheck.contains(annotationName)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkReturnType(MethodTree methodTree) {
		MethodSymbol methodSymbol = methodTree.symbol();
		Type returnType = methodSymbol.returnType().type();
		List<String> returnTypeNamesToCheck = getReturnTypeNames();
		if (returnTypeNamesToCheck.contains(returnType.fullyQualifiedName())) {
			return isAnnotatedWithBean(methodTree);
		}
		return false;
	}

	private boolean isAnnotatedWithBean(MethodTree methodTree) {
		List<AnnotationTree> annotations = methodTree.modifiers().annotations();
		for (AnnotationTree annotation : annotations) {
			if ("Bean".equalsIgnoreCase(annotation.annotationType().symbolType().name())) {
				return true;
			}
		}
		return false;
	}
}
