package com.deloitte.java.testrule;

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
