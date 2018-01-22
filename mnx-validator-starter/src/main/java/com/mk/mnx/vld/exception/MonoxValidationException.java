package com.mk.mnx.vld.exception;

import java.util.List;

public class MonoxValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681874293137548374L;

	private List<MonoxValidatorError> errors;
	
	public MonoxValidationException(String message) {
		super(message);
	}
	
	public MonoxValidationException(String message , List<MonoxValidatorError> errors) {
		super(message);
		this.errors = errors;
	}

	public MonoxValidationException(String message , Throwable cause) {
		super(message, cause);
	}
	
	public MonoxValidationException(Throwable cause) {
		super(cause);
	}
	
	public List<MonoxValidatorError> getErrors() {
		return errors;
	}

}
