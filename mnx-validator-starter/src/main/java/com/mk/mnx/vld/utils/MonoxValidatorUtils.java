package com.mk.mnx.vld.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.ParamName;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.exception.MonoxValidationException;

public class MonoxValidatorUtils {

	
	public static List<Rule> getRulesInMethod(Method method) throws NoSuchMethodException, SecurityException, MonoxValidationException {
    	List<Rule> lRule = new ArrayList<Rule>();
    	Rules rules  = method.getAnnotation(Rules.class);
    	if( rules != null && rules.value().length != 0) {
    		lRule.addAll(Arrays.asList(rules.value()));
    	}
    	Parameter[] parameters = method.getParameters();
    	for(Parameter p : parameters)
    	{
    		Rule paramRule = p.getAnnotation(Rule.class);
    		if(paramRule != null) {
    			lRule.add(paramRule);
    		}
    	}
    	for(ExternalRule externalRule : rules.externalRules()) {
	    	if(externalRule.classRule() != null && externalRule.mehtodRule() != null) {
	    		Method m = externalRule.classRule().getMethod(externalRule.mehtodRule());
	    		lRule.addAll(getRulesInMethod(m));
	    	}
	    	else {
	    		throw new MonoxValidationException("The attributes over External Rule Annotation classRule and mehtodRule are required");
	    	}
    	}
    	return lRule;
    }
    
	public static Map<String, Object> getParameterNameValueMap(Method method, Object[] args) {
        Map<String, Object> annotatedParameters = new HashMap<>();
        Parameter[] parameters = method.getParameters();

        int i = 0;
        for (Parameter p : parameters) {
            Object arg = args[i++];
            ParamName pName = p.getAnnotation(ParamName.class);
            String name = pName == null ?   p.getName() :pName.value()  ;
            annotatedParameters.put(name, arg);
        }
        return annotatedParameters;
    }
    
}