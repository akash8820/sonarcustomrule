package com.deloitte.java.sonarcustomrule.rules.sonarcheck.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

class RateLimiterCheckRuleTest {

	@Test
	void detectedRateLimiterAnnotation() {
		// Use an instance of the check under test to raise the issue.
		RateLimiterCheckRule check = new RateLimiterCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/ratelimit/RateLimiterAnnotationTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}
	
	@Test
	void detectedRateLimiterReturnWithBean() {
		// Use an instance of the check under test to raise the issue.
		RateLimiterCheckRule check = new RateLimiterCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/ratelimit/RateLimiterReturnWithBeanTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}
	
	@Test
	void detectedRateLimiterBucketAndImport() {
		// Use an instance of the check under test to raise the issue.
		RateLimiterCheckRule check = new RateLimiterCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/ratelimit/RateLimiterBucketAndImportTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}
}
