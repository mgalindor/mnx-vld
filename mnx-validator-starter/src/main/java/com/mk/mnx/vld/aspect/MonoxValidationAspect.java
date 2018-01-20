package com.mk.mnx.vld.aspect;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonoxValidationAspect {
    
    @Around("@annotation(com.mk.mnx.vld.annotation.Validate)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

     System.out.println("logAround() is running!");
     System.out.println("hijacked method : " + joinPoint.getSignature().getName());
     System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

     System.out.println("Around before is running!");
     Object o =  joinPoint.proceed(); //continue on the intercepted method
     System.out.println("Around after is running!");

     System.out.println("******");

     return o;
    }

}
