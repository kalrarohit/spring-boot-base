package com.springboot.base.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springboot.base.exception.MessageValidationException;
import com.springboot.base.exception.SpringBootBaseAppException;
import com.springboot.base.msg.IRequestMessage;
import com.springboot.base.msg.IResponseMessage;

/**
 * @author 
 * Default methods were leveraged in this interface to avoid AbstartClass, as 
 * its is observed that method invocation is taking more time for Java8
 * @param <T>
 * @param <U>
 */
public interface IService<U extends IRequestMessage, T extends IResponseMessage> {

	static final Logger log = LoggerFactory.getLogger(IService.class);
	void preProcess(U requestMessage) throws SpringBootBaseAppException,Exception;
	
	
	T process(U requestMessage) throws SpringBootBaseAppException;

	T postProcess(T responseMessage) throws SpringBootBaseAppException,Exception;
	
	/**
	 * This method define the processing flow for all service class
	 * Individual Service class need provide the logic in preProcess, process and postProcess 
	 * method.
	 * 
	 * Individual Impl clasess can override this method as per the need
	 * 
	 */
	default T processRequest(U requestMessage) throws SpringBootBaseAppException,Exception{
		log.info("Inside IService DefaultM-processRequest");
		preProcess(requestMessage);
		log.info("After IService DefaultM-preProcess");
		validateRequest(requestMessage);
		log.info("After IService DefaultM-validateRequest");
		T response = process(requestMessage);
		log.info("After IService DefaultM-process");
		return postProcess(response );		
		
	}
	/**
	 * * Individual Impl might AVOID overriding this method, unless its absolute need.
	 */
	default  void validateRequest(U requestMessage) throws SpringBootBaseAppException,MessageValidationException{
		requestMessage.validate();
	}
}
