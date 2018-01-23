package com.mk.mnx.vld.starter.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;

import com.mk.mnx.vld.constant.MonoxValidatorConstants;
import com.mk.mnx.vld.utils.StaticMessageUtils;

@Configuration
@ComponentScan(basePackages = {MonoxValidatorConstants.BASE_COMPONENT_SCAN})
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class ActiveMNXValidatorAutoConfiguration {
	
	
	@Bean
    public MessageSource monoxValidatorMessageSource (MnxValidatorProperties properties) {
        ReloadableResourceBundleMessageSource messageSource =
                                        new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(properties.getMessages() != null ? properties.getMessages():  MonoxValidatorConstants.RESOURCE_MESSAGE_PATH);
        messageSource.setCacheMillis(500);
        StaticMessageUtils.setMessageSource(messageSource);
        return messageSource;
    }

}
