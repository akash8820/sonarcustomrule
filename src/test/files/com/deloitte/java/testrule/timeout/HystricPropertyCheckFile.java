package com.deloitte.java.testrule.timeout;

public class HystricPropertyCheckFile {

	 @HystrixCommand(fallbackMethod = "fallbackHello",
             commandProperties = {
                 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                 @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                 @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                 @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
             })
public String sayHello(String name) {}
}
