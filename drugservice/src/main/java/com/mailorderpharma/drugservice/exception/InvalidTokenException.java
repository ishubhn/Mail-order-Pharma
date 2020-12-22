package com.mailorderpharma.drugservice.exception;

/**Custom exception class*/
@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {

	/**
	 * @param message
	 */
	public InvalidTokenException(String message){
		super(message);
	}
}
