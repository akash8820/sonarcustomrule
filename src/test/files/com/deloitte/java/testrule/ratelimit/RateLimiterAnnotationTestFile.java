package com.deloitte.java.testrule.ratelimit;

public class RateLimiterAnnotationTestFile {	
	
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {} 

	@VariousAnnotation
	public void differentMethod() {} 
	
	@RateLimiter
	public void rateLimiterAnnotation() {
	}
	

}
