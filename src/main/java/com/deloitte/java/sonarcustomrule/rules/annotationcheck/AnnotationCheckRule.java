package com.deloitte.java.sonarcustomrule.rules.annotationcheck;

import java.util.List;

import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

public abstract class AnnotationCheckRule extends BaseTreeVisitor implements JavaFileScanner {

	protected abstract List<String> getAnnotationNames();

	protected abstract String getMessage();

	private JavaFileScannerContext context;

	@RuleProperty(description = "Name of the annotation")
	protected String name;

	private boolean isAnnotationPresent = false;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
		if (!isAnnotationPresent) {
			context.reportIssue(this, context.getTree(),
					String.format(getMessage() + " (None of the specified annotations found)"));
		}
	}

	@Override
	public void visitMethod(MethodTree tree) {
		List<AnnotationTree> annotations = tree.modifiers().annotations();
		List<String> annotationNamesToCheck = getAnnotationNames();
		for (AnnotationTree annotationTree : annotations) {
			TypeTree annotationType = annotationTree.annotationType();
			if (annotationType.is(Tree.Kind.IDENTIFIER)) {
				String annotationName = ((IdentifierTree) annotationType).name();
				if (annotationNamesToCheck.contains(annotationName)) { // Check if the annotation is in the list
					isAnnotationPresent = true; // Set flag to true if any specified annotation is found
					return;
				}
			}
		}
		super.visitMethod(tree);
	}

}
