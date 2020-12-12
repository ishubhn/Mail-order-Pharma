package com.mailorderpharma.authservice.service;


public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
		super(message);
	}

}
