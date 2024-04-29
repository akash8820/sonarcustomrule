package com.deloitte.java.sonarcustomrule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

import com.deloitte.java.sonarcustomrule.rules.TimeLimiterAnnotationMandatoryRule;

public final class RulesList {

	private RulesList() {
	}

	public static List<Class<? extends JavaCheck>> getChecks() {
		List<Class<? extends JavaCheck>> checks = new ArrayList<>();
		checks.addAll(getJavaChecks());
		return Collections.unmodifiableList(checks);
	}

	/**
	 * These rules are going to target MAIN code only
	 */
	public static List<Class<? extends JavaCheck>> getJavaChecks() {
		return Collections.unmodifiableList(Arrays.asList(TimeLimiterAnnotationMandatoryRule.class));
	}
}