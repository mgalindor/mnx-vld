package com.mk.mnx.vld.exception;

import java.util.List;

import com.mk.mnx.vld.model.ValidationConstraintError;

public class MonoxValidationConstraintException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681874293137548374L;

	private List<ValidationConstraintError> errors;
	
	public MonoxValidationConstraintException(String message) {
		super(message);
	}
	
	public MonoxValidationConstraintException(String message , List<ValidationConstraintError> errors) {
		super(message);
		this.errors = errors;
	}

	public MonoxValidationConstraintException(String message , Throwable cause) {
		super(message, cause);
	}
	
	public MonoxValidationConstraintException(Throwable cause) {
		super(cause);
	}
	
	public List<ValidationConstraintError> getErrors() {
		return errors;
	}

}
