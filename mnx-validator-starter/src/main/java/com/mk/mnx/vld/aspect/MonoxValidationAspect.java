package com.mk.mnx.vld.aspect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.utils.MonoxValidatorUtils;

@Aspect
@Component
public class MonoxValidationAspect {
    
    @Around("@annotation(com.mk.mnx.vld.annotation.Validate)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
     MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
     Method method = methodSignature.getMethod();
     
     List<Rule> rules = MonoxValidatorUtils.getRulesInMethod(method);
     Map<String, Object > params = MonoxValidatorUtils.getParameterNameValueMap(method , joinPoint.getArgs());
     
     Object o =  joinPoint.proceed(); 
     return o;
    }
    

}
