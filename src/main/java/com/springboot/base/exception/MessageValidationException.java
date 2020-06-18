package com.springboot.base.exception;

import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import com.springboot.base.SpringBootBaseAppContextProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * This AppException do not use the error key field instead, it's contains a
 * error key map to hold multiple error messages. This exception class
 * constructed with default error code and refreshed when new error key added or
 * new erroKey map is added to the exception object
 * 
 * @author
 *
 */
@Slf4j
public class MessageValidationException extends SpringBootBaseAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * This map contains key as 'error key' and values as array of Object of all
	 * message anchor element
	 * 
	 * i.e. Say there is entry like global.error.nohandlerfound=Resource not present
	 * for # {0}! in message properties file and we want to show that error message
	 * to user, then 'errorKey' map will contain key = 'global.error.nohandlerfound'
	 * value = new Object[]{"PUT"}
	 * 
	 */

	private Map<String, Object[]> errorKeyMap = new IdentityHashMap<String, Object[]>(10);

	public MessageValidationException() {
		super("global.app.internal.error");
	}

	/**
	 * 
	 * @return The a instance of IdentityHashMap which contains error key and value
	 *         associated with this exception
	 */
	public Map<String, Object[]> getErrorKeyMap() {
		return errorKeyMap;
	}

	/**
	 * Set the error key and value for this exception
	 * 
	 * @param errorKeyMap
	 */
	public void setErrorKeyMap(Map<String, Object[]> errorKeyMap) {
		this.errorKeyMap = errorKeyMap;

	}

	/**
	 * Add the error key and value to the IdentityHashMap, if you want to add
	 * multiple error message with same errorKey then use 'new String("key")' as key
	 * else existing value will be replaced with new value of the key
	 * 
	 * @param key
	 * @param value
	 */
	public void addErrorKeyValue(String key, Object[] value) {
		errorKeyMap.put(key, value);

	}

	@Override
	public String getMessage() {
		Locale locale = LocaleContextHolder.getLocale();
		MessageSource messageSource = SpringBootBaseAppContextProvider.getBean("messageSource", MessageSource.class);

		// Preparing the error message. All validation error messages are coming in
		// single string but separated comma
		StringBuilder errorMessage = new StringBuilder();

		for (Map.Entry<String, Object[]> entry : this.getErrorKeyMap().entrySet()) {
			String simpleMsg = "";
			try {
				simpleMsg = messageSource.getMessage(entry.getKey(), entry.getValue(), locale);
			} catch (NoSuchMessageException e) {
				log.info("Message not defined for error key {}", entry.getKey());
				simpleMsg = entry.getKey();
			}
			if (!errorMessage.toString().isEmpty()) {
				errorMessage.append(", ").append(simpleMsg);
			} else {
				errorMessage.append(simpleMsg);
			}

		}

		return errorMessage.toString();
	}

}
