package com.deloitte.java.sonarcustomrule.rules.annotationcheck;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

@Rule(key = "TimeOutAnnotationRule")
public class TimeOutAnnotationCheckRule extends AnnotationCheckRule {

	private static final List<String> ANNOTATION_NAMES = new ArrayList<>();

	static {
		ANNOTATION_NAMES.add("TimeLimiter");
		ANNOTATION_NAMES.add("HystrixCommand");
	}

	@Override
	protected List<String> getAnnotationNames() {
		return ANNOTATION_NAMES;
	}

	@Override
	protected String getMessage() {
		return "Timeout not implemented in project";
	}
}
