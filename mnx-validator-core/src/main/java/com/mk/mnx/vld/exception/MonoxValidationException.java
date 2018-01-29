package com.mk.mnx.vld.exception;

public class MonoxValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681874293137548374L;

	
	public MonoxValidationException(String message) {
		super(message);
	}
	

	public MonoxValidationException(String message , Throwable cause) {
		super(message, cause);
	}
	
	public MonoxValidationException(Throwable cause) {
		super(cause);
	}
	
}
