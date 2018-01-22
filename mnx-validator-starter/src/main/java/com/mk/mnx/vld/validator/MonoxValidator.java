package com.mk.mnx.vld.validator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.exception.MonoxValidationException;
import com.mk.mnx.vld.exception.MonoxValidatorError;

@Component
public class MonoxValidator {

	public void valid(List<Rule> rules, Map<String, Object> params)
			throws MonoxValidationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<MonoxValidatorError> errors = new ArrayList<MonoxValidatorError>();
		for (Rule rule : rules) {
			if (rule.parameter() == null) {
				throw new MonoxValidationException("The attribute parameter over Rule is required");
			}
			Object o = params.get(rule.parameter());
			if (o == null) {
				throw new MonoxValidationException(String.format("The parameter %s doesn't exist", rule.parameter()));
			}
			String field = rule.path() == null ? String.valueOf(o) : BeanUtils.getProperty(o, rule.path());

			field = rule.trim() ? field.trim() : field;

			if (field == null && rule.required()) {
				errors.add(new MonoxValidatorError());
			} else if (field != null) {
				if (rule.minlength() != -1) {

				}
				if (rule.maxlength() != -1) {

				}
				if (rule.minvalue() != -1) {

				}
				if (rule.maxvalue() != -1) {

				}
				if (rule.mask() != null) {
					Pattern pat = Pattern.compile(rule.mask());
					Matcher mat = pat.matcher(field);
				}
			}
		}
	}

}
