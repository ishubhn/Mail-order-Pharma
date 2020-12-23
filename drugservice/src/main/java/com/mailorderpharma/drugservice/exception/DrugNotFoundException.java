package com.mailorderpharma.drugservice.exception;

/**Custom exception class*/
@SuppressWarnings("serial")
public class DrugNotFoundException extends RuntimeException{

	/**
	 * @param message
	 */
	public DrugNotFoundException(String message) {
		super(message);
	}
}
