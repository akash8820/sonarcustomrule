package com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.SonarCustomCheckRule;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategyImpl;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.ImportCheckStategyImpl;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.SuperClassCheckStategyImpl;

@Rule(key = "TimeOutAnnotationRule")
public class TimeOutCheckRule extends SonarCustomCheckRule {

	public TimeOutCheckRule() {
		super(new AnnotationCheckStrategyImpl(), new ImportCheckStategyImpl(), new SuperClassCheckStategyImpl());
	}

	private static final List<String> ANNOTATION_NAMES = new ArrayList<>();

	static {
		ANNOTATION_NAMES.add("TimeLimiter");
		ANNOTATION_NAMES.add("HystrixCommand");
	}

	private static final List<String> SUPERCLASS_NAMES = new ArrayList<>();

	static {
		SUPERCLASS_NAMES.add("HystrixCommand");
	}
	
	private static final List<String> PROPERTY_NAMES = new ArrayList<>();

	static {
		PROPERTY_NAMES.add("execution.isolation.thread.timeoutInMilliseconds");
	}

	@Override
	protected List<String> getAnnotationNames() {
		return ANNOTATION_NAMES;
	}

	@Override
	protected String getMessage() {
		return "Timeout not implemented in project";
	}

	@Override
	protected List<String> getReturnTypeNames() {
		return null;
	}

	@Override
	protected List<String> getimportNames() {
		return null;
	}

	@Override
	protected List<String> getSuperClassNames() {
		return SUPERCLASS_NAMES;
	}

	@Override
	protected List<String> getPropertyNames() {
		return PROPERTY_NAMES;
	}

}
