package com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.SonarCustomCheckRule;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategyImpl;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.ImportCheckStategyImpl;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.SuperClassCheckStategyImpl;

@Rule(key = "RateLimiterAnnotationRule")
public class RateLimiterCheckRule extends SonarCustomCheckRule {

	public RateLimiterCheckRule() {
		super(new AnnotationCheckStrategyImpl(), new ImportCheckStategyImpl(),new SuperClassCheckStategyImpl());
	}

	private static final List<String> ANNOTATION_NAMES = new ArrayList<>();

	static {
		ANNOTATION_NAMES.add("RateLimiter");
	}

	private static final List<String> RETUNTYPE_NAMES = new ArrayList<>();

	static {
		RETUNTYPE_NAMES.add("Bucket");
		RETUNTYPE_NAMES.add("RateLimiter");
		RETUNTYPE_NAMES.add("KeyResolver");

	}

	private static final List<String> IMPORT_NAMES = new ArrayList<>();

	static {
		IMPORT_NAMES.add("io.github.bucket4j.Bucket");
		IMPORT_NAMES.add("org.springframework.cloud.gateway.filter.ratelimit.KeyResolver");
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

	@Override
	protected List<String> getimportNames() {
		return IMPORT_NAMES;
	}

	@Override
	protected List<String> getSuperClassNames() {
		return null;
	}

	@Override
	protected List<String> getPropertyNames() {
		return null;
	}
}
