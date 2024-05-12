package com.deloitte.java.testrule.timeout;

public class HystrixCommandAnnotationTestFile {
	
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {} 

	@VariousAnnotation
	public void differentMethod() {} 
	
	@HystrixCommand
	public void bMethod() {
	}

}
