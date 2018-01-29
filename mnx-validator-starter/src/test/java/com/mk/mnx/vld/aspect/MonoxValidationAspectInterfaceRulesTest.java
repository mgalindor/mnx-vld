package com.mk.mnx.vld.aspect;

import java.lang.reflect.UndeclaredThrowableException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mk.mnx.vld.ValidationContextConfigurationTest;
import com.mk.mnx.vld.aspect.service.ISimpleService;
import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.DefaultErrorType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationContextConfigurationTest.class)
public class MonoxValidationAspectInterfaceRulesTest {
	
	@Autowired
	private ISimpleService simpleService;
	
	@Test
	public void interfaceBasicRule() {
		simpleService.interfaceBasicRule("Mike");
	}

	@Test
	public void interfaceBasicRuleException() {
		try {
			simpleService.interfaceBasicRule(null);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.REQUIRED.getType()));
			Assert.assertEquals("name", mvce.getErrors().get(0).getParameter());
		}
	}
	
}
