package com.mk.mnx.vld.messages;

import java.util.Locale;

public interface MessageReader {
	
	public String getMessage(String key , Object ... args);
	
	public String getMessage(String key , Locale locale ,Object ... args);

}
