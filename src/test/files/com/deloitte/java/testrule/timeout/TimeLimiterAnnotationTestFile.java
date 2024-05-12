package com.deloitte.java.testrule.timeout;

import java.util.ArrayList;
import java.util.List;

import org.sonar.check.Rule;

//Don't format this class
public class TimeLimiterAnnotationTestFile {

	@TimeLimiter
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {} 

	@VariousAnnotation
	public void differentMethod() {} 


}
