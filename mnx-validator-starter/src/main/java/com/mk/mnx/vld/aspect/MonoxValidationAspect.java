package com.mk.mnx.vld.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.Constraint;
import com.mk.mnx.vld.model.ValidationConstraintError;
import com.mk.mnx.vld.utils.MonoxValidatorUtils;
import com.mk.mnx.vld.validator.MonoxValidator;

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

		List<Constraint> constraints = monoxValidatorUtils.getRulesInMethod(method);
		Map<String, Object> params = monoxValidatorUtils.getParameterNameValueMap(method, joinPoint.getArgs());

		try {
			monoxValidator.valid(constraints, params);
		} catch (MonoxValidationConstraintException mvce) {
			
			//If there is an argument of type List<ValidationConstraintError> the list
			//of erros will be assigned in other case the erros will be propagated in form
			//of an exception
			boolean propagate = true;
	        for (Object arg : joinPoint.getArgs()) {
	        	if(arg instanceof List ) {
	        		Class<?> clazz = arg.getClass();
	        		ParameterizedType superclass = (ParameterizedType) clazz.getGenericSuperclass();
	        		Type[] types = superclass.getActualTypeArguments();
	        		if(types != null && types.length >0 && (types[0] instanceof ValidationConstraintError) ) {
	        		    arg = mvce.getErrors();
	        		    propagate = false;
	        		    break;
	        		}
	        	}
	        }
	        if(propagate) {
	        	throw mvce;
	        }
		}

		Object o = joinPoint.proceed();
		return o;
	}

}
