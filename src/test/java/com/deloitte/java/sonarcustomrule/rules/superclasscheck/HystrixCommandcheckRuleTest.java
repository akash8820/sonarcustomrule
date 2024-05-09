package com.deloitte.java.sonarcustomrule.rules.superclasscheck;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

class HystrixCommandcheckRuleTest {

	@Test
	void detected() {
		// Use an instance of the check under test to raise the issue.
		SuperClassNameCheckRule check = new SuperClassNameCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/HystrixCommandcheckRuleTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/HystrixCommandcheckRuleTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();
	}
}
