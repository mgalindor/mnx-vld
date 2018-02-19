package com.mk.mnx.vld.validator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.ParamName;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.exception.MonoxValidationException;
import com.mk.mnx.vld.model.Constraint;
import com.mk.mnx.vld.model.ValidationConstraintError;

public class MonoxValidatorUtils {
	
	/**
	 * This method looks for all {@link com.mk.mnx.vld.annotation.Rule rules} to be
	 * applied in the validation of a method. The
	 * {@link com.mk.mnx.vld.annotation.Rule rules} will be looked for in: the
	 * method their super classes parameters
	 * {@link com.mk.mnx.vld.annotation.ExternalRule External rules}
	 * 
	 * @param method
	 *            The method where will be applied a validation
	 * @return List of constraints that describes the behavior of the validation
	 *         that will be applied over the method
	 * @throws NoSuchMethodException
	 *             The method parameter in a External rule does not exist
	 * @throws SecurityException
	 *             There method referenced in a External rule is private
	 * @throws MonoxValidationException
	 *             There are missing parameters in a External Rule
	 */
	public List<Constraint> getRulesInMethod(Method method)
			throws NoSuchMethodException, SecurityException, MonoxValidationException {
		List<Constraint> lRule = new ArrayList<Constraint>();
		Rules rules =  MethodUtils.getAnnotation(method, Rules.class, true, true);
		if (rules != null && rules.value().length != 0) {
			lRule.addAll(
					Arrays.asList(rules.value()).stream().map(x -> new Constraint(x)).collect(Collectors.toList()));

			for (ExternalRule externalRule : rules.externalRules()) {
				if (externalRule.classRule() != null && externalRule.mehtodRule() != null) {
					Method m = externalRule.classRule().getMethod(externalRule.mehtodRule());
					lRule.addAll(getRulesInMethod(m));
				} else {
					throw new MonoxValidationException(
							"The attributes over External Rule Annotation classRule and mehtodRule are required");
				}
			}
		}

		if (rules != null && rules.externalRules().length != 0) {

			for (ExternalRule externalRule : rules.externalRules()) {
				if (externalRule.classRule() != null && externalRule.mehtodRule() != null) {
					Method m = externalRule.classRule().getDeclaredMethod(externalRule.mehtodRule());
					lRule.addAll(getRulesInMethod(m));
				} else {
					throw new MonoxValidationException(
							"The attributes over External Rule Annotation classRule and mehtodRule are required");
				}
			}
		}

		Parameter[] parameters = method.getParameters();
		for (Parameter param : parameters) {
			Rule paramRule = param.getAnnotation(Rule.class);
			if (paramRule != null) {
				ParamName pName = param.getAnnotation(ParamName.class);
				String name = pName == null ? param.getName() : pName.value();
				Constraint c = new Constraint(paramRule);
				c.setParameter(name);
				lRule.add(c);
			}
		}

		return lRule;
	}

	/**
	 * This method generate a map with all parameter names over a method and their
	 * values
	 * 
	 * @param method
	 *            Method of a class
	 * @param args
	 *            Arguments to be used over this method
	 * @return Map with key parameter name and value
	 */
	public Map<String, Object> getParameterNameValueMap(Method method, Object[] args) {
		Map<String, Object> annotatedParameters = new HashMap<>();
		Parameter[] parameters = method.getParameters();

		int i = 0;
		for (Parameter p : parameters) {
			Object arg = args[i++];
			ParamName pName = p.getAnnotation(ParamName.class);
			String name = pName == null ? p.getName() : pName.value();
			annotatedParameters.put(name, arg);
		}
		return annotatedParameters;
	}

	/**
	 * This method try to assign the list erros in one element of the array args if
	 * there is an element over the args parameter of type
	 * List of ValidationConstraintError the list errors will be assigned on it
	 * 
	 * @param args
	 *            Array of objects
	 * @param errors
	 *            List of errors to be assigned
	 * @return True if there was a parameter of type List of ValidationConstraintError
	 *         and the list of errors was assigned else false
	 */
	public boolean assignErrorsIfExistParameter(Object[] args, List<ValidationConstraintError> errors) {
		boolean propagate = false;
		for (Object arg : args) {
			if (arg instanceof List) {
				Class<?> clazz = arg.getClass();
				ParameterizedType superclass = (ParameterizedType) clazz.getGenericSuperclass();
				Type[] types = superclass.getActualTypeArguments();
				if (types != null && types.length > 0 && (types[0] instanceof ValidationConstraintError)) {
					arg = errors;
					propagate = true;
					break;
				}
			}
		}
		return propagate;
	}

}
