package com.mk.mnx.vld.exception;

import java.util.List;

public class MonoxValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681874293137548374L;

	private List<ValidationConstraintError> errors;
	
	public MonoxValidationException(String message) {
		super(message);
	}
	
	public MonoxValidationException(String message , List<ValidationConstraintError> errors) {
		super(message);
		this.errors = errors;
	}

	public MonoxValidationException(String message , Throwable cause) {
		super(message, cause);
	}
	
	public MonoxValidationException(Throwable cause) {
		super(cause);
	}
	
	public List<ValidationConstraintError> getErrors() {
		return errors;
	}

}
