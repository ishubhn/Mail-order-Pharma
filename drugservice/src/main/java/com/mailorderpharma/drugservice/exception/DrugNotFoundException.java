package com.mailorderpharma.drugservice.exception;

public class DrugNotFoundException extends RuntimeException{

	public DrugNotFoundException(String message) {
		super(message);
	}
}
