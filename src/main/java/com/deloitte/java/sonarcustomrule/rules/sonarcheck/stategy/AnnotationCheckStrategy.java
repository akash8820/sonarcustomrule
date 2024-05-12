package com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy;

import java.util.List;

import org.sonar.plugins.java.api.tree.MethodTree;

public interface AnnotationCheckStrategy {
    boolean checkAnnotation(MethodTree methodTree, List<String> annotationNamesToCheck);
    boolean checkReturnTypeAndAnnotatedWithBean(MethodTree methodTree, List<String> returnTypeNamesToCheck);
}
