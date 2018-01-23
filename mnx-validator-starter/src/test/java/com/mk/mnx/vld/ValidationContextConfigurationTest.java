package com.mk.mnx.vld;

import java.util.Locale;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;

import com.mk.mnx.vld.starter.annotation.EnableMonoxValidator;

@SpringBootApplication
@EnableMonoxValidator
public class ValidationContextConfigurationTest {
	
	@Bean
	public LocaleContext localeContextHolder() {
	    LocaleContextHolder.setDefaultLocale(new Locale("ES"));
	    return LocaleContextHolder.getLocaleContext();
	}

}
