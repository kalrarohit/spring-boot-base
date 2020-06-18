package com.springboot.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetails {

	/**
	 * The detail description of the error
	 */
	@JsonProperty("description")
	private String errorDescription;
	/**
	 * Generic message
	 */
	private String message;	
	/**
	 * The error description 
	 * @return
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * Set the description of the error
	 * @param errorDescription
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	/**
	 * Get the error message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Set the error message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
