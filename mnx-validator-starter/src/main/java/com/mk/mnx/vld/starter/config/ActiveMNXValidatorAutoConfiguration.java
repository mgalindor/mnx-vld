package com.mk.mnx.vld.starter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;

import com.mk.mnx.vld.constant.MonoxValidatorConstants;
import com.mk.mnx.vld.messages.I18nMessageReader;
import com.mk.mnx.vld.messages.MessageReader;
import com.mk.mnx.vld.validator.MonoxValidator;
import com.mk.mnx.vld.validator.MonoxValidatorUtils;

@Configuration
@ComponentScan(basePackages = {MonoxValidatorConstants.BASE_COMPONENT_SCAN})
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class ActiveMNXValidatorAutoConfiguration {
	
	
	@Bean("monoxValidatorMessageSource")
	@ConditionalOnProperty(  name ="monox.validation.messages")
	@ConditionalOnMissingBean(name="monoxValidatorMessageSource")
    public MessageSource monoxValidatorMessageSource (MnxValidatorProperties properties) {
        ReloadableResourceBundleMessageSource messageSource =
                                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(properties.getMessages());
        messageSource.setCacheMillis(1000);
        return messageSource;
    }
	
	@Bean("monoxValidatorMessageSource")
	@ConditionalOnProperty(name="monox.validation.messages" , matchIfMissing=true)
	@ConditionalOnMissingBean(name="monoxValidatorMessageSource")
    public MessageSource monoxValidatorMessageSourceDefault (MnxValidatorProperties properties) {
        ReloadableResourceBundleMessageSource messageSource =
                                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MonoxValidatorConstants.RESOURCE_MESSAGE_PATH);
        messageSource.setCacheMillis(1000);
        return messageSource;
    }
	
	@Bean("messageReader")
	@ConditionalOnMissingBean(name="messageReader")
	@ConditionalOnBean(name="monoxValidatorMessageSource")
	public MessageReader messageReader(@Qualifier("monoxValidatorMessageSource") MessageSource messageSource) {
		I18nMessageReader monoxSpringMessageReader = new I18nMessageReader(messageSource);
		return monoxSpringMessageReader;
	}
	
	@Bean
	@ConditionalOnMissingBean(MonoxValidatorUtils.class)
	public MonoxValidatorUtils monoxValidatorUtils() {
		return new MonoxValidatorUtils();
	}
	
	@Bean
	@ConditionalOnMissingBean(MonoxValidator.class)
	@ConditionalOnBean(name="messageReader")
	public MonoxValidator monoxValidator(@Qualifier("messageReader")  MessageReader messageReader) {
		return new MonoxValidator(messageReader);
	}

}
