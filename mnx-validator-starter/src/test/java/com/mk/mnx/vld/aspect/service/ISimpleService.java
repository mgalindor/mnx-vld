package com.mk.mnx.vld.aspect.service;

import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.annotation.Validate;

public interface ISimpleService {

	@Validate
    @Rules(@Rule(parameter="name",required=true ) ) 
	public String interfaceBasicRule(String name) ;
	
}
