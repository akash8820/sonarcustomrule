package com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.SonarCustomCheckRule;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategy;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategyImpl;

@Rule(key = "RateLimiterAnnotationRule")
public class RateLimiterAnnotationCheckRule extends SonarCustomCheckRule {

	public RateLimiterAnnotationCheckRule(AnnotationCheckStrategy annotationCheckStrategy) {
		super(new AnnotationCheckStrategyImpl());
	}

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
