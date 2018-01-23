package com.mk.mnx.vld.validator;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.enu.DefaultErrorType;
import com.mk.mnx.vld.exception.MonoxValidationException;
import com.mk.mnx.vld.exception.ValidationConstraintError;

@Component
public class MonoxValidator {

	public void valid(List<Rule> rules, Map<String, Object> params)
			throws MonoxValidationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<ValidationConstraintError> errors = new ArrayList<ValidationConstraintError>();
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
				errors.add(new ValidationConstraintError( DefaultErrorType.REQUIRED , rule.path()  , rule.path() ));
			} else if (field != null) {
				if (rule.minlength() != -1 && field.length() < rule.minlength()) {
					errors.add(new ValidationConstraintError( DefaultErrorType.WRONG_MIN_LENGTH , rule.path()  , rule.path(), rule.minlength()));
				}
				if (rule.maxlength() != -1 && field.length() > rule.maxlength()) {
					errors.add(new ValidationConstraintError( DefaultErrorType.WRONG_MIN_LENGTH , rule.path()  , rule.path(), rule.maxlength()));
				}
				if (rule.minvalue() != -1 && StringUtils.isNumeric(field) && new BigDecimal(field).compareTo(new BigDecimal(rule.minvalue())) == -1 ) {
					errors.add(new ValidationConstraintError( DefaultErrorType.WRONG_MIN_VALUE , rule.path()  , rule.path(), rule.minvalue()));
				}
				if (rule.maxvalue() != -1 && StringUtils.isNumeric(field) && new BigDecimal(field).compareTo(new BigDecimal(rule.maxvalue())) == 1 ) {
					errors.add(new ValidationConstraintError( DefaultErrorType.WRONG_MAX_VALUE , rule.path()  , rule.path(), rule.maxvalue()));
				}
				if (rule.mask() != null) {
					Pattern pattern = Pattern.compile(rule.mask());
					Matcher matcher = pattern.matcher(field);
					if(!matcher.matches()) {
						errors.add(new ValidationConstraintError( DefaultErrorType.WRONG_MASK , rule.path()  , rule.path(), rule.mask()));
					}
				}
			}
		}
		if(!errors.isEmpty()) {
			throw new MonoxValidationException("There are some errors", errors);
		}
	}

}
