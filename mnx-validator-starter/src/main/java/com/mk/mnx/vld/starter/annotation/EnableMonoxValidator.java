package com.mk.mnx.vld.starter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.mk.mnx.vld.starter.config.ActiveMNXValidatorAutoConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ActiveMNXValidatorAutoConfiguration.class)
public @interface EnableMonoxValidator {

}
