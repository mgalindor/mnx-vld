package com.mk.mnx.vld.enu;

public enum DefaultErrorType implements ErrorType {
	
	REQUIRED ("monox.validation.required.valueNotPresent") ,
	WRONG_MIN_LENGTH ("monox.validation.minlength.valueTooShort"),
	WRONG_MAX_LENGTH ("monox.validation.maxlength.valueTooLong"),
	WRONG_MIN_VALUE ("monox.validation.minvalue.valueBelowMinimum"),
	WRONG_MAX_VALUE ("monox.validation.maxvalue.valueAboveMaximum"),
	WRONG_MASK ("monox.validation.mask.valueDoesNotMatch"), 

	;
	
	private String key;
	
	DefaultErrorType(String key){
		this.key=key;
	}
	
	@Override
	public String getKey() {
		return key;
	}


	@Override
	public String getType() {
		return this.name();
	}
	
}
