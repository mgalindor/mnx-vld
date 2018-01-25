package com.mk.mnx.vld.aspect.service.impl;

import org.springframework.stereotype.Service;

import com.mk.mnx.vld.annotation.Validate;
import com.mk.mnx.vld.aspect.service.ISimpleService;

@Service
public class SimpleService implements ISimpleService {

    @Override
    @Validate
	public String interfaceBasicRule(String name) {
    	return "Hello "+name;
	}

}
