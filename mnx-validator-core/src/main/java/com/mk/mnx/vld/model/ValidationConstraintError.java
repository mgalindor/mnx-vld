package com.mk.mnx.vld.model;

import com.mk.mnx.vld.enu.ErrorType;

public class ValidationConstraintError {
	
	private String message;
	
	private ErrorType type;
	
	private String  parameter;
	
	private String path;
	
	private Object [] args;
	
	public ValidationConstraintError(String message , ErrorType type , String parameter, String path, Object ... args) {
		this.message = message;
		this.type = type;
		this.parameter = parameter;
		this.path = path;
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public ErrorType getType() {
		return type;
	}

	public String getParameter() {
		return parameter;
	}

	public String getPath() {
		return path;
	}
	
	public Object[] getArgs() {
		return args;
	}
}
