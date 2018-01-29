package com.mk.mnx.vld.messages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class ClassPathFileMessageReader implements MessageReader {

	Properties properties;

	public ClassPathFileMessageReader(String filename) throws IOException {
		properties = new Properties();
		InputStream input = ClassPathFileMessageReader.class.getClassLoader().getResourceAsStream(filename);
		properties.load(input);
	}

	/**
	 * This method will look for a key in a properties file, the result will be
	 * parsed using String.format with the args parameter
	 * 
	 * Ex. foo=Foo [%s] is required
	 * 
	 * getMessage("foo" , "bar")
	 * 
	 * result "Foo [bar] is required"
	 * 
	 * @param key
	 *            Is the key that will be looked into the properties file
	 * @param args
	 *            This parameters are a list of objects to be replaced in the
	 *            message founded
	 * @return The message founded in a properties file formatted with the args
	 *         parameter
	 * 
	 */
	@Override
	public String getMessage(String key, Object... args) {
		return String.format(properties.getProperty(key), args);
	}

	/**
	 * This method will look for a key in a properties file, the result will be
	 * parsed using String.format with the args parameter
	 * 
	 * Ex. foo=Foo [%s] is required
	 * 
	 * getMessage("foo" , "bar")
	 * 
	 * result "Foo [bar] is required"
	 * 
	 * @param key
	 *            Is the key that will be looked into the properties file
	 * @param Locale
	 *            This parameter is not used
	 * @param args
	 *            This parameters are a list of objects to be replaced in the
	 *            message founded
	 * @return The message founded in a properties file formatted with the args
	 *         parameter
	 * 
	 */
	@Override
	public String getMessage(String key, Locale locale, Object... args) {
		return getMessage(key, args);
	}

}
