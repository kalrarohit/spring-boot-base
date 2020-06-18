package com.springboot.base.msg;

import com.springboot.base.ErrorDetails;

public class SpringBootBaseErrorResponse implements IResponseMessage {

	/**
	 * Error code and message for this response
	 */
	private ErrorDetails errorDetails = new ErrorDetails();

	@Override
	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	@Override
	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;

	}
	
	/**
	 * Set the error description to the ErrorDetails of error response
	 * @param errorDescription
	 */
	public void setErrorDescription(String errorDescription) {
		errorDetails.setErrorDescription(errorDescription);
	}
	/**
	 * Add error message to ErrorDetails
	 * @param message
	 */
	public void addErrorMessage(String message) {
		errorDetails.setMessage(message);
		
	}

}
