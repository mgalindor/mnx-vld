package com.mk.mnx.vld.starter.config;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@ComponentScan(basePackages = {"com.mk.mnx.vld"})
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class ActiveMNXValidatorAutoConfiguration {

}
