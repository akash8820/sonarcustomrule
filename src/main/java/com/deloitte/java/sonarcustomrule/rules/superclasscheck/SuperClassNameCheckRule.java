package com.deloitte.java.sonarcustomrule.rules.superclasscheck;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

@Rule(key = "TimeOutAnnotationRule")
public class SuperClassNameCheckRule extends SuperClassCheckRule {

	private static final List<String> SUPERCLASS_NAMES = new ArrayList<>();

	static {
		SUPERCLASS_NAMES.add("HystrixCommand");
	}

	@Override
	protected String getMessage() {
		return "Super class not implemented which is required for timeout";
	}


	@Override
	protected List<String> getSuperClassNames() {
		return SUPERCLASS_NAMES;
	}

}
