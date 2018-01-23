package com.mk.mnx.vld.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class StaticMessageUtils {
	
	private static MessageSource messageSource;
	
	public static void setMessageSource(MessageSource messageSource) {
		StaticMessageUtils.messageSource = messageSource;
	}
	
	public static String getMessage(String key , Object ... args) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(key , locale, args);
	}
	
	public static String getMessage(String key , Locale locale ,Object ... args) {
		return messageSource.getMessage(key, args, locale);
	}
	

}
