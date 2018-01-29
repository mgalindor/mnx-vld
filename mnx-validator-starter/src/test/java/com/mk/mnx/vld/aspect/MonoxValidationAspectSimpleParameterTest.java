package com.mk.mnx.vld.aspect;

import java.lang.reflect.UndeclaredThrowableException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mk.mnx.vld.ValidationContextConfigurationTest;
import com.mk.mnx.vld.aspect.model.Detail;
import com.mk.mnx.vld.aspect.model.Fly;
import com.mk.mnx.vld.aspect.model.Pilot;
import com.mk.mnx.vld.aspect.service.ISimpleService;
import com.mk.mnx.vld.aspect.service.impl.SimpleComponent;
import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.DefaultErrorType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationContextConfigurationTest.class)
public class MonoxValidationAspectSimpleParameterTest {

	@Autowired
	private SimpleComponent simpleComponent;
	
	@Autowired
	private ISimpleService simpleService;

	@Test
	public void contextValidation() {
		Assert.assertNotNull(simpleComponent);
	}

	@Test
	public void nonRulesTest() {
		simpleComponent.nonRules("Mike");
	}

	@Test
	public void nonRule() {
		simpleComponent.nonRule("Mike");
	}

	@Test
	public void basicRuleCorrect() {
		simpleComponent.basicRule("Mike");
	}

	@Test
	public void basicRuleException() {
		try {
			simpleComponent.basicRule(null);
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

	@Test
	public void basicRuleParamNameAnnotation() {
		simpleComponent.basicRuleParamNameAnnotation("Mike");
	}

	@Test
	public void basicRuleParamNameAnnotationException() {
		try {
			simpleComponent.basicRuleParamNameAnnotation(null);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.REQUIRED.getType()));
			Assert.assertEquals("otherName", mvce.getErrors().get(0).getParameter());
		}
	}

	@Test
	public void basicRuleOverParam() {
		simpleComponent.basicRuleOverParam("Mike");
	}

	@Test
	public void basicRuleOverParamException() {
		try {
			simpleComponent.basicRuleOverParam(null);
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

	@Test
	public void basicRuleOverParamWithAnnotation() {
		simpleComponent.basicRuleOverParamWithAnnotation("Mike");
	}

	@Test
	public void basicRuleOverParamWithAnnotationException() {
		try {
			simpleComponent.basicRuleOverParamWithAnnotation(null);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.REQUIRED.getType()));
			Assert.assertEquals("otherName", mvce.getErrors().get(0).getParameter());
		}
	}
	
	
	@Test
	public void basicExternalRule() {
		simpleComponent.basicExternalRule("Mike");
	}

	@Test
	public void basicExternalRuleException() {
		try {
			simpleComponent.basicExternalRule(null);
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
