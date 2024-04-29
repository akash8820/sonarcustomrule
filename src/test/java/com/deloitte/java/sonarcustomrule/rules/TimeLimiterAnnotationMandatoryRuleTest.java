package com.deloitte.java.sonarcustomrule.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

public class TimeLimiterAnnotationMandatoryRuleTest {

	@Test
	void detected() {
		// Use an instance of the check under test to raise the issue.
		TimeLimiterAnnotationMandatoryRule check = new TimeLimiterAnnotationMandatoryRule();

		// define the mandatory annotation name
		String name = "TimeLimiter";

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/TimeLimiterAnnotationMandatoryTestFile.java")
				.withCheck(check).verifyNoIssues();

	}

}
