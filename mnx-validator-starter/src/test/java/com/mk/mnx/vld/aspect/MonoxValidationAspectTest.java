package com.mk.mnx.vld.aspect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mk.mnx.vld.ValidationContextConfigurationTest;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationContextConfigurationTest.class)
public class MonoxValidationAspectTest {
    
    @Autowired
    private SimpleComponent simpleComponent;

    @Test
    public void contextValidation() {
        Assert.assertNotNull(simpleComponent);
    }
    
    
    @Test
    public void aspectValidation() {
        String greating = simpleComponent.greating("mike");
        Assert.assertEquals("Hello mike", greating);
        System.out.println("################# "+greating);
        
        simpleComponent.greatingNoReturn("");
        
    }
}
