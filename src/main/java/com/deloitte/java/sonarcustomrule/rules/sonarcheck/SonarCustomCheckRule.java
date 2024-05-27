package com.deloitte.java.sonarcustomrule.rules.sonarcheck;

import java.util.List;
import java.util.Objects;

import org.sonar.check.RuleProperty;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategy;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.ImportCheckStrategy;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.SuperClassCheckStategy;

public abstract class SonarCustomCheckRule extends IssuableSubscriptionVisitor implements JavaFileScanner {

	@RuleProperty(description = "Name of the annotation")
	protected String name;

	private boolean annotationPresent;
	private boolean returnTypeAndAnnotatedWithBean;
	private boolean importPresent;
	private boolean checkReturnType;
	private boolean checkReturnandImportPresent;
	private boolean checkSuperClassPresent;
	private boolean propertyAnnotationPresent;
	private AnnotationCheckStrategy annotationCheckStrategy;
	private ImportCheckStrategy importCheckStrategy;
	private SuperClassCheckStategy superClassCheckStategy;

	public SonarCustomCheckRule(AnnotationCheckStrategy annotationCheckStrategy,
			ImportCheckStrategy importCheckStrategy, SuperClassCheckStategy superClassCheckStategy) {
		this.annotationCheckStrategy = annotationCheckStrategy;
		this.importCheckStrategy = importCheckStrategy;
		this.superClassCheckStategy = superClassCheckStategy;
	}

	@Override
	public List<Kind> nodesToVisit() {
		return List.of(Tree.Kind.METHOD, Tree.Kind.IMPORT, Tree.Kind.CLASS);
	}

	@Override
	public void visitNode(Tree tree) {
		if (tree.is(Tree.Kind.METHOD)) {
			annotationPresent = annotationCheckStrategy.checkAnnotation(tree, getAnnotationNames())
					|| annotationPresent;
			if (annotationPresent) {
				propertyAnnotationPresent = annotationCheckStrategy.checkPropertyAnnotation(tree, getPropertyNames());
			}
			returnTypeAndAnnotatedWithBean = annotationCheckStrategy.checkReturnTypeAndAnnotatedWithBean(tree,
					getReturnTypeNames()) || returnTypeAndAnnotatedWithBean;
			checkReturnType = importCheckStrategy.checkReturnType(tree, getReturnTypeNames());
		} else if (tree.is(Tree.Kind.IMPORT)) {
			importPresent = importCheckStrategy.checkImport(tree, getimportNames()) || importPresent;
		} else if (tree.is(Tree.Kind.CLASS)) {
			checkSuperClassPresent = superClassCheckStategy.checkSuperClass(tree, getSuperClassNames());
		}

		if (checkReturnType && importPresent)
			checkReturnandImportPresent = Boolean.TRUE;

	}

	@Override
	public void leaveFile(JavaFileScannerContext context) {
		if (!annotationPresent && !returnTypeAndAnnotatedWithBean && !checkReturnandImportPresent
				&& !checkSuperClassPresent && !propertyAnnotationPresent) {
			reportIssue(context.getTree(), getClassName(context) + ": " + getMessage());
		}
		annotationPresent = false;
		returnTypeAndAnnotatedWithBean = false;
		checkReturnandImportPresent = false;
		checkSuperClassPresent = false;
	}

	private String getClassName(JavaFileScannerContext context) {
		Objects.requireNonNull(context, "JavaFileScannerContext cannot be null.");
		String uri = context.getInputFile().uri().toString();
		return uri.substring(uri.lastIndexOf('/') + 1);
	}

	protected abstract List<String> getAnnotationNames();

	protected abstract List<String> getReturnTypeNames();

	protected abstract List<String> getimportNames();

	protected abstract List<String> getSuperClassNames();

	protected abstract List<String> getPropertyNames();

	protected abstract String getMessage();

}
