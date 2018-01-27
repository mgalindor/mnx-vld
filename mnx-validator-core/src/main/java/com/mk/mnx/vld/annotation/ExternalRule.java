package com.mk.mnx.vld.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
//@Target(METHOD)
public @interface ExternalRule {
	
	Class<?> classRule() ;
    
    String mehtodRule();

}
