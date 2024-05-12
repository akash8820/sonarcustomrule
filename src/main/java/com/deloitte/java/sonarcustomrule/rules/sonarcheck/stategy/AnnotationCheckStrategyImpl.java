package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

public class AnnotationCheckStrategyImpl implements AnnotationCheckStrategy {

	@Override
	public boolean checkAnnotation(MethodTree methodTree, List<String> annotationNamesToCheck) {
		List<AnnotationTree> annotations = methodTree.modifiers().annotations();
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

	@Override
	public boolean checkReturnTypeAndAnnotatedWithBean(MethodTree methodTree, List<String> returnTypeNamesToCheck) {
		MethodSymbol methodSymbol = methodTree.symbol();
		Type returnType = methodSymbol.returnType().type();
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
