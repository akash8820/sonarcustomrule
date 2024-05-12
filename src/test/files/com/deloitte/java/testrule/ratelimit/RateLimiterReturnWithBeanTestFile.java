package com.deloitte.java.testrule.ratelimit;

public class RateLimiterReturnWithBeanTestFile {
	
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {} 

	@VariousAnnotation
	public void differentMethod() {} 
	
	@Bean
	public RateLimiter rateLimiterAnnotation() {
	}

}
