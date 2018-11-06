package com.mk.mnx.vld.aspect;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.Constraint;
import com.mk.mnx.vld.validator.MonoxValidator;
import com.mk.mnx.vld.validator.MonoxValidatorUtils;

@Aspect
@Component
public class MonoxValidationAspect {

	@Autowired
	private MonoxValidator monoxValidator;

	@Autowired
	private MonoxValidatorUtils monoxValidatorUtils;

	@Around("@annotation(com.mk.mnx.vld.annotation.Validate)")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Method method = methodSignature.getMethod();

		Set<Constraint> constraints = new HashSet<Constraint>(monoxValidatorUtils.getRulesInMethod(method));
		Map<String, Object> params = monoxValidatorUtils.getParameterNameValueMap(method, joinPoint.getArgs());

		try {
			monoxValidator.valid(constraints, params);
		} catch (MonoxValidationConstraintException mvce) {
			boolean trw = monoxValidatorUtils.assignErrorsIfExistParameter(joinPoint.getArgs(), mvce.getErrors());
			if (!trw) {
				throw mvce;
			}
		}

		Object o = joinPoint.proceed();
		return o;
	}

}
