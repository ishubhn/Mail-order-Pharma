package com.mailorderpharma.subscription.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mailorderpharma.subscription.entity.ConstraintErrorResponse;
import com.mailorderpharma.subscription.entity.ErrorMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorMessage> invalidTokenException(InvalidTokenException invalidTokenException) {
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), invalidTokenException.getMessage()),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(SubscriptionListEmptyException.class)
	public  ResponseEntity<ErrorMessage> subscriptionListEmptyException(SubscriptionListEmptyException subscriptionListEmptyException) {
		return new ResponseEntity<ErrorMessage>(
				new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), subscriptionListEmptyException.getMessage()),
				HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(feign.RetryableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorMessage serviceUnavailableException() {
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, LocalDateTime.now(), "Temporarily service unavailable");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> constraintValidationException(ConstraintViolationException constraintViolationException)
	{
		List<String> errorMessages = new ArrayList<String>();
		for(ConstraintViolation<?> constraintViolation:constraintViolationException.getConstraintViolations()) 
		{
			errorMessages.add(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
		}
		return ResponseEntity.badRequest().body(new ConstraintErrorResponse(HttpStatus.BAD_REQUEST,LocalDateTime.now()
																			,errorMessages));
		
	}
}