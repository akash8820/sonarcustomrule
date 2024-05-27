package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.tree.Tree;

public interface AnnotationCheckStrategy {
    boolean checkAnnotation(Tree tree, List<String> annotationNamesToCheck);
    boolean checkReturnTypeAndAnnotatedWithBean(Tree Tree, List<String> returnTypeNamesToCheck);
    public boolean checkPropertyAnnotation(Tree tree, List<String> propertiesToCheck);
}
