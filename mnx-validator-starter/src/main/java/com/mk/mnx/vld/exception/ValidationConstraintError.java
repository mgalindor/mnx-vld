package com.mk.mnx.vld.exception;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.mk.mnx.vld.enu.ErrorType;
import com.mk.mnx.vld.utils.StaticMessageUtils;

public class ValidationConstraintError {
	
	private String message;
	
	private ErrorType type;
	
	private String  parameter;
	
	private String path;
	
	public ValidationConstraintError(String message , ErrorType type , String parameter, String path) {
		this.message = message;
		this.type = type;
		this.parameter = parameter;
		this.path = path;
	}

	public ValidationConstraintError(ErrorType type , String parameter, String path , Object ... args) {
		String paramName = path != null ? path.replace(".", " ")    : parameter;
		
		this.message = StaticMessageUtils.getMessage(type.getKey(), ArrayUtils.add(args, StringUtils.capitalize(paramName)));
		
		this.type = type;
		this.parameter = parameter;
		this.path = path;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorType getType() {
		return type;
	}

	public void setType(ErrorType type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
