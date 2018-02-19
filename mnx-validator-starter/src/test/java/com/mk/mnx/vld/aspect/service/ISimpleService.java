package com.mk.mnx.vld.aspect.service;

import com.mk.mnx.vld.annotation.ParamName;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;

public interface ISimpleService {

    @Rules(@Rule(parameter="name",required=true ) ) 
	public String interfaceBasicRule(@ParamName("name")  String name) ;
	
}
