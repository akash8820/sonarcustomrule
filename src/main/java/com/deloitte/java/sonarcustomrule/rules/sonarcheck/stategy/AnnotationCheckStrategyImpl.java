package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;
import java.util.Optional;

import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.AssignmentExpressionTree;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.NewArrayTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

public class AnnotationCheckStrategyImpl implements AnnotationCheckStrategy {

	@Override
	public boolean checkAnnotation(Tree tree, List<String> annotationNamesToCheck) {
		MethodTree methodTree = (MethodTree) tree;
		List<AnnotationTree> annotations = methodTree.modifiers().annotations();
		for (AnnotationTree annotationTree : annotations) {
			TypeTree annotationType = annotationTree.annotationType();
			if (annotationType.is(Tree.Kind.IDENTIFIER)) {
				String annotationName = ((IdentifierTree) annotationType).name();
				if (annotationNamesToCheck != null && annotationNamesToCheck.contains(annotationName)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean checkReturnTypeAndAnnotatedWithBean(Tree tree, List<String> returnTypeNamesToCheck) {
		MethodTree methodTree = (MethodTree) tree;
		MethodSymbol methodSymbol = methodTree.symbol();
		Type returnType = methodSymbol.returnType().type();
		if (returnTypeNamesToCheck != null && returnTypeNamesToCheck.contains(returnType.fullyQualifiedName())) {
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

	@Override
	public boolean checkPropertyAnnotation(Tree tree, List<String> propertiesToCheck) {
		if (!(tree instanceof MethodTree)) {
			return false;
		}

		MethodTree methodTree = (MethodTree) tree;
		List<AnnotationTree> annotations = methodTree.modifiers().annotations();

		for (AnnotationTree annotationTree : annotations) {
			Symbol.TypeSymbol annotationSymbol = Optional
					.ofNullable(annotationTree.annotationType().symbolType().symbol()).orElse(null);
			if (annotationSymbol != null && "HystrixCommand".equals(annotationSymbol.name())) {
				for (ExpressionTree argument : annotationTree.arguments()) {
					if (argument instanceof AssignmentExpressionTree) {
						AssignmentExpressionTree assignment = (AssignmentExpressionTree) argument;
						String variable = assignment.variable().toString();
						if ("commandProperties".equals(variable)) {
							NewArrayTree array = (NewArrayTree) Optional.ofNullable(assignment.expression())
									.orElse(null);
							if (array != null) {
								for (ExpressionTree element : array.initializers()) {
									if (element instanceof AnnotationTree) {
										AnnotationTree propertyAnnotation = (AnnotationTree) element;
										String propName = null;
										for (ExpressionTree propExpr : propertyAnnotation.arguments()) {
											if (propExpr instanceof AssignmentExpressionTree) {
												AssignmentExpressionTree propAssignment = (AssignmentExpressionTree) propExpr;
												String variableName = propAssignment.variable().toString();
												if ("name".equals(variableName)) {
													propName = getLiteralValue(propAssignment.expression());
												}
											}
										}
										if (propName != null && propertiesToCheck.stream().anyMatch(propName::equals)) {
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	private String getLiteralValue(ExpressionTree expression) {
		if (expression instanceof LiteralTree) {
			LiteralTree literal = (LiteralTree) expression;
			String value = literal.value();
			if (value.startsWith("\"") && value.endsWith("\"")) {
				value = value.substring(1, value.length() - 1);
			}
			return value;
		}
		return expression.toString();
	}
}
