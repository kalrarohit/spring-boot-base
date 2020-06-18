/**
 * 
 */
package com.springboot.base.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import com.springboot.base.SpringBootBaseAppContextProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author
 * 
 * 		This Exception is design to generate business exceptions
 *
 */
@Slf4j
public class SpringBootBaseAppException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7281622693658016522L;
	/**
	 * Error key for this exception which will be use to lookup the error messages
	 */
	private String erroKey;
	/**
	 * Values for place holders
	 */
	private Object[] placeHolderValues;

	/**
	 * Construct the new exception with all details
	 * 
	 * @param key
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SpringBootBaseAppException(String key, Throwable arg1, boolean arg2, boolean arg3) {
		super(key, arg1, arg2, arg3);
		this.erroKey = key;

	}

	/**
	 * Construct the new exceptions with erroKey and throwable
	 * 
	 * @param key
	 * @param arg1
	 */
	public SpringBootBaseAppException(String key, Throwable arg1) {
		super(key, arg1);
		this.erroKey = key;

	}

	/**
	 * Construct the new exception with errorKey only
	 * 
	 * @param key
	 */
	public SpringBootBaseAppException(String key) {
		super(key);
		this.erroKey = key;
	}

	/**
	 * Construct the new exception with throwable
	 * 
	 * @param arg0
	 */
	public SpringBootBaseAppException(Throwable arg0) {
		super(arg0);

	}

	/**
	 * Construct the new exception with error key and all the replacement values in
	 * error message
	 * 
	 * @param erroKey
	 * @param placeHolderValues
	 */
	public SpringBootBaseAppException(String erroKey, Object... placeHolderValues) {
		super(erroKey);
		this.erroKey = erroKey;
		this.placeHolderValues = placeHolderValues;
	}

	/**
	 * 
	 * @return The error key
	 */
	public String getErroKey() {
		return erroKey;
	}

	/**
	 * The place holder values of the error message
	 * 
	 * @return
	 */
	public Object[] getPlaceHolderValues() {
		return placeHolderValues;
	}

	@Override
	public String getMessage() {
		Locale locale = LocaleContextHolder.getLocale();
		MessageSource messageSource = SpringBootBaseAppContextProvider.getBean("messageSource", MessageSource.class);
		StringBuilder msgBuilder = new StringBuilder();

		try {
			if (erroKey != null) {
				msgBuilder.append(messageSource.getMessage(erroKey, placeHolderValues, locale));
			} else {
				msgBuilder.append("Application Exception");
			}
		} catch (NoSuchMessageException e) {
			log.error("Error message not defined for the key: {}", erroKey);
			msgBuilder.append(erroKey);
		}

		return msgBuilder.toString();
	}

}
