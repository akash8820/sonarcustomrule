package com.deloitte.java.testrule.ratelimit;

public class RateLimitKeyResolverWithbeanAnnotated {
	
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {
	}

	@VariousAnnotation
	public void differentMethod() {
	}

	@Bean
	public KeyResolver userRemoteAddrKeyResolver() {
		return new UserRemoteAddrKeyResolver();
	}


}
