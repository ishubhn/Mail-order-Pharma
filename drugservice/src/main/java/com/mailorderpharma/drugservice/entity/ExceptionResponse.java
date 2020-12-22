package com.mailorderpharma.drugservice.entity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Model class for the business details*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	/**
	 * Message that the exception throws
	 */
	String messge;
	/**
	 * Timestamp for the exception
	 */
	LocalDateTime timestamp;
	/**
	 * Http status
	 */
	HttpStatus status;
}
