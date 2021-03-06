package com.mk.mnx.vld.aspect.service.impl;

import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.ParamName;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.annotation.Validate;
import com.mk.mnx.vld.aspect.model.Fly;

@Component
public class SimpleComponent{
    
    @Validate
    public String nonRules( String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules
    public String nonRule( String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules(@Rule(parameter="name",required=true )  ) 
    public String basicRule( String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules(@Rule(parameter="otherName",required=true )  ) 
    public String basicRuleParamNameAnnotation(@ParamName("otherName") String name) {
        return "Hello "+name;
    }
    
    
    @Validate
    public String basicRuleOverParam(@Rule(required=true ) String name) {
        return "Hello "+name;
    }
    
    @Validate
    public String basicRuleOverParamWithAnnotation(@Rule(parameter="otherName",required=true )  @ParamName("otherName") String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules(externalRules = { @ExternalRule(classRule=SimpleComponent.class, mehtodRule="rules" )    }  )
    public String basicExternalRule(String name) {
        return "Hello "+name;
    }
    
    @Rules(@Rule(parameter="name",required=true )  ) 
    private void rules() {}
    
    @Validate
    @Rules( {@Rule(parameter="fly",path="name" ,required=true ),
    		@Rule(parameter="fly",path="pilot.ranking" ,required=true ),
    		@Rule(parameter="fly",path="pilot.detail.age" ,required=true )
    }) 
    public void basicRuleOverModel( Fly fly) {
    }

    @Validate
    @Rules(@Rule(parameter="name",minlength=2 )  ) 
    public String minLengthRule( String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules(@Rule(parameter="name",maxlength=5 )  ) 
    public String maxLengthRule( String name) {
        return "Hello "+name;
    }
    
    @Validate
    @Rules(@Rule(parameter="age",minvalue=2.5 )  ) 
    public String minValRule( Double age) {
        return "Hello "+age;
    }
    
    @Validate
    @Rules(@Rule(parameter="age",maxvalue=10 )  ) 
    public String maxValRule( Integer age) {
        return "Hello "+age;
    }
}
