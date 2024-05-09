package com.deloitte.java.testrule;

import org.springframework.context.annotation.Bean;

public class ReturnAndBeanTestFile {

    @Bean
    public TimeLimiter methodWithBeanAnnotation() {
    }

    public void methodWithoutAnnotation() {
    }
}
