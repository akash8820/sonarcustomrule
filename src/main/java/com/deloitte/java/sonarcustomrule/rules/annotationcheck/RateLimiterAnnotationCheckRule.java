package com.deloitte.java.sonarcustomrule.rules.annotationcheck;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

@Rule(key = "RateLimiterAnnotationRule")
public class RateLimiterAnnotationCheckRule extends AnnotationCheckRule {

	private static final List<String> ANNOTATION_NAMES = new ArrayList<>();

	static {
		ANNOTATION_NAMES.add("RateLimiter");
	}

	private static final List<String> RETUNTYPE_NAMES = new ArrayList<>();

	static {
		RETUNTYPE_NAMES.add("RateLimiter");
	}

	@Override
	protected List<String> getAnnotationNames() {
		return ANNOTATION_NAMES;
	}

	@Override
	protected String getMessage() {
		return "rate limiter not implemented in project";
	}

	@Override
	protected List<String> getReturnTypeNames() {
		return RETUNTYPE_NAMES;
	}
}

