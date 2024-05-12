package com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

import com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl.TimeOutCheckRule;
import com.deloitte.java.sonarcustomrule.rules.sonarcheck.stategy.AnnotationCheckStrategyImpl;

public class TimeOutMandatoryRuleTest {

	@Test
	void detectedTimeLimiter() {
		// Use an instance of the check under test to raise the issue.
		TimeOutCheckRule check = new TimeOutCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/timeout/TimeLimiterAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}

	@Test
	void detectedHystrixCommand() {
		TimeOutCheckRule check = new TimeOutCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/timeout/HystrixCommandAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}
	
	@Test
	void detectedHystrixSuperClass() {
		TimeOutCheckRule check = new TimeOutCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/timeout/HystrixSuperClassTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}

}
