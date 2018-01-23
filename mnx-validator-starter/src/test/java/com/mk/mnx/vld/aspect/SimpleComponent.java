package com.mk.mnx.vld.aspect;

import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.ExternalRule;
import com.mk.mnx.vld.annotation.ParamName;
import com.mk.mnx.vld.annotation.Rule;
import com.mk.mnx.vld.annotation.Rules;
import com.mk.mnx.vld.annotation.Validate;

@Component
public class SimpleComponent {
    
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

}
