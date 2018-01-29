package com.mk.mnx.vld.validator;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.mk.mnx.vld.enu.ErrorType;
import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.exception.MonoxValidationException;
import com.mk.mnx.vld.messages.MessageReader;
import com.mk.mnx.vld.model.Constraint;
import com.mk.mnx.vld.model.DefaultErrorType;
import com.mk.mnx.vld.model.ValidationConstraintError;

public class MonoxValidator {

	private MessageReader messageReader;

	public MonoxValidator(MessageReader messageReader) {
		this.messageReader = messageReader;
	}

	public void valid(List<Constraint> constraints, Map<String, Object> params)
			throws MonoxValidationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			MonoxValidationConstraintException {
		List<ValidationConstraintError> errors = new ArrayList<ValidationConstraintError>();
		for (Constraint constraint : constraints) {
			if (constraint.getParameter() == null) {
				throw new MonoxValidationException("The attribute parameter over Rule is required");
			}

			if (!params.containsKey(constraint.getParameter())) {
				throw new MonoxValidationException(
						String.format("The parameter [%s] doesn't exist", constraint.getParameter()));
			}
			Object o = params.get(constraint.getParameter());
			String field = getFieltToBeValidated(o, constraint);

			errors.addAll(validField(field, constraint));
		}
		if (!errors.isEmpty()) {
			throw new MonoxValidationConstraintException("There are some errors", errors);
		}
	}

	public String getFieltToBeValidated(Object object, Constraint constraint)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String field = StringUtils.EMPTY;
		if (object != null) {
			if (StringUtils.isEmpty(constraint.getPath())) {
				field = String.valueOf(object);
			} else {
				Object nobject = BeanUtils.getProperty(object, constraint.getPath());
				field = nobject == null ? StringUtils.EMPTY : String.valueOf(nobject);
			}
		}
		return field;
	}

	public List<ValidationConstraintError> validField(String field, Constraint constraint) {
		List<ValidationConstraintError> errors = new ArrayList<ValidationConstraintError>();
		if (StringUtils.isEmpty(field) && constraint.isRequired()) {
			errors.add(buildValidationConstraintError(DefaultErrorType.REQUIRED, constraint.getParameter(),
					constraint.getPath()));
		} else if (!StringUtils.isEmpty(field)) {
			if (constraint.getMinlength() != -1 && field.length() < constraint.getMinlength()) {
				errors.add(buildValidationConstraintError(DefaultErrorType.WRONG_MIN_LENGTH, constraint.getParameter(),
						constraint.getPath(), constraint.getMinlength()));
			}
			if (constraint.getMaxlength() != -1 && field.length() > constraint.getMaxlength()) {
				errors.add(buildValidationConstraintError(DefaultErrorType.WRONG_MIN_LENGTH, constraint.getParameter(),
						constraint.getPath(), constraint.getMaxlength()));
			}
			if (constraint.getMinvalue() != -1 && StringUtils.isNumeric(field)
					&& new BigDecimal(field).compareTo(new BigDecimal(constraint.getMinvalue())) == -1) {
				errors.add(buildValidationConstraintError(DefaultErrorType.WRONG_MIN_VALUE, constraint.getParameter(),
						constraint.getPath(), constraint.getMinvalue()));
			}
			if (constraint.getMaxvalue() != -1 && StringUtils.isNumeric(field)
					&& new BigDecimal(field).compareTo(new BigDecimal(constraint.getMaxvalue())) == 1) {
				errors.add(buildValidationConstraintError(DefaultErrorType.WRONG_MAX_VALUE, constraint.getParameter(),
						constraint.getPath(), constraint.getMaxvalue()));
			}
			if (!StringUtils.isEmpty(constraint.getMask())) {
				Pattern pattern = Pattern.compile(constraint.getMask());
				Matcher matcher = pattern.matcher(field);
				if (!matcher.matches()) {
					errors.add(buildValidationConstraintError(DefaultErrorType.WRONG_MASK, constraint.getParameter(),
							constraint.getPath(), constraint.getMask()));
				}
			}
		}
		return errors;
	}

	public ValidationConstraintError buildValidationConstraintError(ErrorType type, String parameter, String path,
			Object... args) {
		String paramName = path != null ? path.replace(".", " ") : parameter;
		String message = messageReader.getMessage(type.getKey(),
				ArrayUtils.add(args, StringUtils.capitalize(paramName)));

		return new ValidationConstraintError(message, type, parameter, path, args);
	}
}
