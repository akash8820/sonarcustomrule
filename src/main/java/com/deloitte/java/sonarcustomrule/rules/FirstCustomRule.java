package com.deloitte.java.sonarcustomrule.rules;

import java.util.Collections;
import java.util.List;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

@Rule(key = "AvoidMethodWithSameTypeInArgument")
public class FirstCustomRule extends IssuableSubscriptionVisitor {

	  @Override
	  public List<Tree.Kind> nodesToVisit() {
	    return Collections.singletonList(Tree.Kind.METHOD);
	  }

	  @Override
	  public void visitNode(Tree tree) {
	    MethodTree methodTree = (MethodTree) tree;
	    MethodSymbol methodSymbol = methodTree.symbol();
	    Type returnType = methodSymbol.returnType().type();
	    if (methodSymbol.parameterTypes().size() == 1) {
	      Type argType = methodSymbol.parameterTypes().get(0);
	      if (argType.is(returnType.fullyQualifiedName())) {
	        reportIssue(tree, "message");
	      }
	    }
	  }
	}
