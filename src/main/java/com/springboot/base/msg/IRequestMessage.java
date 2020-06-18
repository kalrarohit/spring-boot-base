package com.springboot.base.msg;


import com.springboot.base.exception.MessageValidationException;
import com.springboot.base.exception.SpringBootBaseAppException;

public interface IRequestMessage {
	
	/**
	 * This will perform basic validation of the request message and populate the validation message list.
	 * @throws SpringBootBaseAppException
	 * @throws MessageValidationException TODO
	 */
	public void validate() throws SpringBootBaseAppException,MessageValidationException;
	

}
