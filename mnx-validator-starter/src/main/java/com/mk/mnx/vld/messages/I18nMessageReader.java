package com.mk.mnx.vld.messages;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18nMessageReader  implements MessageReader{
	
	private MessageSource messageSource;
	
	public I18nMessageReader(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
	public String getMessage(String key , Object ... args) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(key , locale, args);
	}
	
	@Override
	public String getMessage(String key , Locale locale ,Object ... args) {
		return messageSource.getMessage(key, args, locale);
	}
	

}
