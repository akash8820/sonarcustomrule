package com.deloitte.java.sonarcustomrule.rules.superclasscheck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;
import org.sonar.plugins.java.api.JavaFileScanner;

public class HystrixCommandcheckRuleTest {

	@Test
	void detected() {
		// Use an instance of the check under test to raise the issue.
		SuperClassNameCheckRule check = new SuperClassNameCheckRule();

		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/HystrixCommandcheckRuleTestFile.java")
				.withCheck((JavaFileScanner) check).verifyNoIssues();

	}
}
