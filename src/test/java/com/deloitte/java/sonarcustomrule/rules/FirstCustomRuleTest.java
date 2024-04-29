package com.deloitte.java.sonarcustomrule.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;


class FirstCustomRuleTest {

	@Test
	void test() {
		CheckVerifier.newVerifier()
	      .onFile("src/test/files/com/deloitte/java/testrule/FirstCustomRule.java")
	      .withCheck(new FirstCustomRule())
	      .verifyIssues();
	}

}
