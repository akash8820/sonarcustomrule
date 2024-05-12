package com.deloitte.java.testrule.ratelimit;

import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import org.aspectj.lang.JoinPoint;
import io.github.bucket4j.Bucket;

public class RateLimiterBucketAndImportTestFile {
	
	public void aMethod() {
	}

	@org.foo.TimeLimiter
	public void anotherMethod() {} 

	@VariousAnnotation
	public void differentMethod() {} 
	
	public Bucket rateLimiterAnnotation() {
	}

}
