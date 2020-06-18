package com.springboot.base.exception.handler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.h2.api.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.springboot.base.msg.SpringBootBaseErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Global Exception handler for SpringBoot Base Application
 * 
 * @author
 *
 */
@ControllerAdvice
@RestController
@Slf4j
public class SpringBootBaseAppExceptionHandler {

	private final MessageSource messageSource;

	@Autowired
	public SpringBootBaseAppExceptionHandler(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public SpringBootBaseErrorResponse handleUnsupportMediaType(HttpMediaTypeNotSupportedException e, Locale locale) {
		log.error("Media-Type Not Supported", e);
		SpringBootBaseErrorResponse response = new SpringBootBaseErrorResponse();
		response.addErrorMessage(getMessage("generic.invalid.msg", new Object[] {}, locale));
		response.setErrorDescription(getMessage("input.request.mediatype.unsupported", new Object[] {}, locale));
		return response;
	}

	// System exception handler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public SpringBootBaseErrorResponse noHandlerFoundException(HttpServletRequest request, NoHandlerFoundException e,
			Locale locale) {
		log.error("Handler not define", e);
		SpringBootBaseErrorResponse response = new SpringBootBaseErrorResponse();
		response.addErrorMessage(getMessage("generic.api.internalerror.msg", new Object[] {}, locale));
		response.setErrorDescription(getMessage("global.app.internal.error", new Object[] {}, locale));
		return response;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public SpringBootBaseErrorResponse handleException(Exception e, Locale locale) {
		log.error("Unhandled Exception details ", e);
		SpringBootBaseErrorResponse response = new SpringBootBaseErrorResponse();
		response.addErrorMessage(getMessage("generic.api.internalerror.msg", new Object[] {}, locale));
		response.setErrorDescription(getMessage("global.app.internal.error", new Object[] {}, locale));
		return response;
	}

	/**
	 * Retrieve the message from messageSource based on the errorKey, return empty
	 * string if no message define against key
	 * 
	 * @param errorKey
	 * @param placeHoldersValue
	 * @param locale
	 * @return
	 */
	private String getMessage(String errorKey, Object[] placeHoldersValue, Locale locale) {
		try {
			return messageSource.getMessage(errorKey, placeHoldersValue, locale);
		} catch (NoSuchMessageException e) {
			log.error("No message found for key {}", errorKey);
			return "";
		}

	}

}
