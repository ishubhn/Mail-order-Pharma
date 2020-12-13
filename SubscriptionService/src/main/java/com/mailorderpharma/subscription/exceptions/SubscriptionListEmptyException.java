package com.mailorderpharma.subscription.exceptions;

public class SubscriptionListEmptyException extends RuntimeException {

	public SubscriptionListEmptyException(String message){
		super(message);
	}
}
