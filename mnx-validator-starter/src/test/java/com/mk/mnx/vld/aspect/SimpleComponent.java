package com.mk.mnx.vld.aspect;

import org.springframework.stereotype.Component;

import com.mk.mnx.vld.annotation.Validate;

@Component
public class SimpleComponent {
    
    @Validate
    public String greating( String name) {
        return "Hello "+name;
    }
    
    
    @Validate
    public void greatingNoReturn ( String name) {
    }

}
