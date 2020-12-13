package com.mailorderpharma.subscription.exceptions;

public class InvalidTokenException extends RuntimeException {

	public InvalidTokenException(String message){
		super(message);
	}
}
