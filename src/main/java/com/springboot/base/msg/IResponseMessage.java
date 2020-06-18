package com.springboot.base.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.springboot.base.ErrorDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public interface IResponseMessage {
	/**
	 * The error details of the response message
	 * if there is no error then it should be empty 
	 * @return
	 */
	@JsonProperty("error")
	public ErrorDetails getErrorDetails();
	/**
	 * Set the error message and code of error response
	 * @param errorDetails
	 */
	public void setErrorDetails(ErrorDetails errorDetails);


}
