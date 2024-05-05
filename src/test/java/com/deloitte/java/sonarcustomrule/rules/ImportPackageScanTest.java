package com.deloitte.java.sonarcustomrule.rules;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.CheckVerifier;

class ImportPackageScanTest {

	@Test
	void detected() {
		// Use an instance of the check under test to raise the issue.
		ImportPackageScan check = new ImportPackageScan();


		CheckVerifier.newVerifier()
				.onFile("src/test/files/com/deloitte/java/testrule/ImportPackageScanFile.java")
				.withCheck(check).verifyNoIssues();

	}


}
