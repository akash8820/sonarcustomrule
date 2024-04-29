package com.deloitte.java.sonarcustomrule.rules;

import java.util.List;

import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

@Rule(key = "TimeLimiterAnnotationMandatory")
public class TimeLimiterAnnotationMandatoryRule extends BaseTreeVisitor implements JavaFileScanner {

	// private static final Logger LOGGER =
	// Loggers.get(TimeLimiterAnnotationMandatoryRule.class);

	private static final String DEFAULT_VALUE = "MyTimeLimiterAnnotation";

	private JavaFileScannerContext context;

	@RuleProperty(defaultValue = DEFAULT_VALUE, description = "Name of the mandatory annotation")
	private String name = "TimeLimiter";

	private boolean isAnnotationPresent = false;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
		if (!isAnnotationPresent) {
			context.reportIssue(this, context.getTree(), String.format("Mandatory Annotation @%s not found", name));
		}
	}

	@Override
	public void visitMethod(MethodTree tree) {
		List<AnnotationTree> annotations = tree.modifiers().annotations();

		for (AnnotationTree annotationTree : annotations) {
			TypeTree annotationType = annotationTree.annotationType();
			if (annotationType.is(Tree.Kind.IDENTIFIER)) {
				String annotationName = ((IdentifierTree) annotationType).name();
				if (annotationName.equals(name)) {
					isAnnotationPresent = true;
					break;
				}
			}

		}

		// The call to the super implementation allows to continue the visit of the AST.
		// Be careful to always call this method to visit every node of the tree.
		super.visitMethod(tree);
	}
}
