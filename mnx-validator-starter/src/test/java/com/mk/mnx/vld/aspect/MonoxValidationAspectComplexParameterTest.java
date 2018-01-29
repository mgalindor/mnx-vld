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
import com.mk.mnx.vld.aspect.service.impl.SimpleComponent;
import com.mk.mnx.vld.exception.MonoxValidationConstraintException;
import com.mk.mnx.vld.model.DefaultErrorType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationContextConfigurationTest.class)
public class MonoxValidationAspectComplexParameterTest {

	@Autowired
	private SimpleComponent simpleComponent;
	
	@Test
	public void basicRuleOverModel() {
		Fly fly = new Fly();
		fly.setPilot(new Pilot());
		fly.getPilot().setDetail(new Detail());
		
		fly.setName("Mike");
		fly.getPilot().setRanking("Expert");
		fly.getPilot().getDetail().setAge(23d);
		simpleComponent.basicRuleOverModel(fly);
	}

	@Test
	public void basicRuleOverModelException() {
		try {
			simpleComponent.basicRuleOverModel(null);
			Assert.fail();
		} catch (UndeclaredThrowableException e) {
			Assert.assertTrue(e.getUndeclaredThrowable() instanceof MonoxValidationConstraintException);
			MonoxValidationConstraintException mvce = (MonoxValidationConstraintException) e.getUndeclaredThrowable();
			Assert.assertNotNull(mvce.getErrors());
			Assert.assertFalse(mvce.getErrors().isEmpty());
			Assert.assertEquals(new Integer(3), new Integer (mvce.getErrors().size()));
			Assert.assertTrue(mvce.getErrors().get(0).getType().getType().equals(DefaultErrorType.REQUIRED.getType()));
			Assert.assertEquals("fly", mvce.getErrors().get(0).getParameter());
		}
	}

}
