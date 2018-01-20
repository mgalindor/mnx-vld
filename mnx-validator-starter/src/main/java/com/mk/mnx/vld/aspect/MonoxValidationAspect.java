package com.mk.mnx.vld.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
     
     
     MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
     Method m = methodSignature.getMethod();
     
     Object[] signatureArgs = joinPoint.getArgs();
     for (Map.Entry<String, Object> entry : getAnnotatedParameterValue( m , joinPoint.getArgs() ).entrySet()  ) {
        System.out.println("key: " + entry.getKey());
        System.out.println("param: "+entry.getValue());
     }
     
     Object o =  joinPoint.proceed(); //continue on the intercepted method
     System.out.println("Around after is running!");

     System.out.println("******");

     return o;
    }
    
    
    private Map<String, Object> getAnnotatedParameterValue(Method method, Object[] args) {
        Map<String, Object> annotatedParameters = new HashMap<>();
        Parameter[] parameters = method.getParameters();

        int i = 0;
        for (Parameter p : parameters) {
            Object arg = args[i++];
            String name = p.getName();
            annotatedParameters.put(name, arg);
        }
        return annotatedParameters;
    }

}
