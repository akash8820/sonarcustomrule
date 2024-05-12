package com.deloitte.java.sonarcustomrule.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl.TimeOutAnnotationCheckRule;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategyImpl;

public class TimeLimiterAnnotationMandatoryRuleTest {

	@Test
	void detectedTimeLimiter() {
		// Use an instance of the check under test to raise the issue.
		TimeOutAnnotationCheckRule check = new TimeOutAnnotationCheckRule(new AnnotationCheckStrategyImpl());

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/TimeLimiterAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

		CheckVerifier.newVerifier().onFile("src/test/files/com/deloitte/java/testrule/ReturnAndBeanTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}

	@Test
	void detectedHystrixCommand() {
		// Use an instance of the check under test to raise the issue.
		TimeOutAnnotationCheckRule check = new TimeOutAnnotationCheckRule(new AnnotationCheckStrategyImpl());

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/HystrixCommandAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}

}
