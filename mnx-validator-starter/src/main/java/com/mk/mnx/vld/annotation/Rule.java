package com.mk.mnx.vld.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.PARAMETER})
public @interface Rule {

    String parameter() ;
    
    String path() default "" ;
    
    boolean required() default false;
    
    boolean trim() default true;

    int minlength() default -1;

    int maxlength() default -1;

    double minvalue() default Double.MIN_VALUE;

    double maxvalue() default Double.MAX_VALUE;
    
    String mask() default "";
}
