package com.mk.mnx.vld.aspect;

import java.lang.reflect.UndeclaredThrowableException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mk.mnx.vld.ValidationContextConfigurationTest;
import com.mk.mnx.vld.aspect.service.impl.SimpleComponent;
import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.DefaultErrorType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationContextConfigurationTest.class)
public class MonoxValidationAspectSimpleParameterTest {

	@Autowired
	private SimpleComponent simpleComponent;

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
	public void minLengthRule() {
		simpleComponent.minLengthRule("Mike");
	}

	@Test
	public void minLengthRuleException() {
		try {
			simpleComponent.minLengthRule("M");
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.WRONG_MIN_LENGTH.getType()));
			Assert.assertEquals("name", mvce.getErrors().get(0).getParameter());
		}
	}
	
	@Test
	public void maxLengthRule() {
		simpleComponent.maxLengthRule("Mike");
	}

	@Test
	public void maxLengthRuleException() {
		try {
			simpleComponent.maxLengthRule("MikeLGalen");
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertEquals(DefaultErrorType.WRONG_MAX_LENGTH.getType(),mvce.getErrors().get(0).getType().getType());
			Assert.assertEquals("name", mvce.getErrors().get(0).getParameter());
		}
	}
	
	@Test
	public void minValRule() {
		simpleComponent.minValRule(5d);
	}

	@Test
	public void minValRuleException() {
		try {
			simpleComponent.minValRule(2d);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.WRONG_MIN_VALUE.getType()));
			Assert.assertEquals("age", mvce.getErrors().get(0).getParameter());
		}
	}
	
	@Test
	public void maxValRule() {
		simpleComponent.maxValRule(5);
	}

	@Test
	public void maxValRuleException() {
		try {
			simpleComponent.maxValRule(11);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertTrue(mvce.getErrors().size() == 1);
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.WRONG_MAX_VALUE.getType()));
			Assert.assertEquals("age", mvce.getErrors().get(0).getParameter());
		}
	}
}
