package com.learningsystems.tests.shared.selenium.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Lazy
@Configuration
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LazyConfiguration {
	
}