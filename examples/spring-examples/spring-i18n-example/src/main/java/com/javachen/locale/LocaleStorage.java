package com.javachen.locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleStorage {
	
	@Autowired
	private MessageSource messageSource;
	
	public String get(Locale locale, String key) {
		return messageSource.getMessage(key, null, locale);
	}
}
