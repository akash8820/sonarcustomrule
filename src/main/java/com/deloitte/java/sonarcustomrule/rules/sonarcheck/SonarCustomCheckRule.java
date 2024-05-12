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
import org.sonar.plugins.java.api.tree.ImportTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeTree;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategy;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.ImportCheckStrategy;

public abstract class SonarCustomCheckRule extends IssuableSubscriptionVisitor implements JavaFileScanner {

	@RuleProperty(description = "Name of the annotation")
	protected String name;

	private boolean annotationPresent;
	private boolean returnTypeAndAnnotatedWithBean;
	private boolean importPresent;
	private boolean checkReturnType;
	private boolean checkReturnandImportPresent;
	private AnnotationCheckStrategy annotationCheckStrategy;
	private ImportCheckStrategy importCheckStrategy;

	public SonarCustomCheckRule(AnnotationCheckStrategy annotationCheckStrategy,
			ImportCheckStrategy importCheckStrategy) {
		this.annotationCheckStrategy = annotationCheckStrategy;
		this.importCheckStrategy = importCheckStrategy;
	}

	@Override
	public List<Kind> nodesToVisit() {
		return List.of(Tree.Kind.METHOD, Tree.Kind.IMPORT);
	}

	@Override
	public void visitNode(Tree tree) {
		if (tree.is(Tree.Kind.METHOD)) {
			annotationPresent = annotationCheckStrategy.checkAnnotation(tree, getAnnotationNames())
					|| annotationPresent;
			returnTypeAndAnnotatedWithBean = annotationCheckStrategy.checkReturnTypeAndAnnotatedWithBean(tree,
					getReturnTypeNames()) || returnTypeAndAnnotatedWithBean;
			checkReturnType = importCheckStrategy.checkReturnType(tree, getReturnTypeNames());
		} else if (tree.is(Tree.Kind.IMPORT)) {
			importPresent = importCheckStrategy.checkImport(tree, getimportNames()) || importPresent;
		}
		if (checkReturnType && importPresent)
			checkReturnandImportPresent = Boolean.TRUE;

	}

	@Override
	public void leaveFile(JavaFileScannerContext context) {
		if (!annotationPresent && !returnTypeAndAnnotatedWithBean && !checkReturnandImportPresent) {
			reportIssue(context.getTree(), getMessage());
		}
		annotationPresent = false;
		returnTypeAndAnnotatedWithBean = false;
	}

	protected abstract List<String> getAnnotationNames();

	protected abstract List<String> getReturnTypeNames();

	protected abstract List<String> getimportNames();

	protected abstract String getMessage();

}
