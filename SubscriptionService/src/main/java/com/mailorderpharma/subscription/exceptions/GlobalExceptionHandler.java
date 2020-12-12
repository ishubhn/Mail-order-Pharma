package com.mailorderpharma.subscription.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mailorderpharma.subscription.entity.ErrorMessage;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(FeignException.NotFound.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage notFoundException(FeignException.NotFound feignException)
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND,LocalDateTime.now()," not found");
	}
	
	@ExceptionHandler(feign.RetryableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorMessage serviceUnavailableException() 
	{
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, LocalDateTime.now(), "Temporarily service unavailable");
	}
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> methodArgumentMismatchException(NumberFormatException numberFormatException)
	{
		return ResponseEntity.badRequest().body(new ErrorMessage(HttpStatus.BAD_REQUEST, LocalDateTime.now()
				, "must be number"));
	}
}