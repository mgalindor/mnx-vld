package com.mk.mnx.vld.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mk.mnx.vld.annotation.Rule;

public class Constraint {
	
	private String parameter;
    
	private String path;
    
	private boolean required;
    
	private boolean trim;

	private int minlength;

	private int maxlength;

	private double minvalue;

	private double maxvalue;
    
	private String mask;
	
	public Constraint(Rule rule) {
		parameter = rule.parameter();
		path = rule.path();
		required = rule.required();
		trim = rule.trim();
		minlength = rule.minlength();
		maxlength = rule.maxlength();
		minvalue = rule.minvalue();
		maxvalue = rule.maxvalue();
		mask = rule.mask();
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

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public int getMinlength() {
		return minlength;
	}

	public void setMinlength(int minlength) {
		this.minlength = minlength;
	}

	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	public double getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(double minvalue) {
		this.minvalue = minvalue;
	}

	public double getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(double maxvalue) {
		this.maxvalue = maxvalue;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, true);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, true);
	}
}
