package com.deloitte.java.testrule.ratelimit;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;

public class RateLimitKeyResolverWithCloudGateway {

	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {
	}

	@VariousAnnotation
	public void differentMethod() {
	}

	public KeyResolver userRemoteAddrKeyResolver() {
		return new UserRemoteAddrKeyResolver();
	}

}
