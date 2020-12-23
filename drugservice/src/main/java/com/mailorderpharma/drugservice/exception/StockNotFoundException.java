package com.mailorderpharma.drugservice.exception;

/**Custom exception class*/
@SuppressWarnings("serial")
public class StockNotFoundException extends Exception {

	/**
	 * @param message
	 */
	public StockNotFoundException(String message) {
		super(message);
	}
}
