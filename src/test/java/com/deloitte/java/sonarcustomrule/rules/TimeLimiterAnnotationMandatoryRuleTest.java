package com.deloitte.java.sonarcustomrule.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

import com.deloitte.java.sonarcustomrule.rules.annotationcheck.TimeOutAnnotationCheckRule;

public class TimeLimiterAnnotationMandatoryRuleTest {

	@Test
	void detectedTimeLimiter() {
		// Use an instance of the check under test to raise the issue.
		TimeOutAnnotationCheckRule check = new TimeOutAnnotationCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/TimeLimiterAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();
	}
	
	@Test
	void detectedHystrixCommand() {
		// Use an instance of the check under test to raise the issue.
		TimeOutAnnotationCheckRule check = new TimeOutAnnotationCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/HystrixCommandAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();
	}
}
