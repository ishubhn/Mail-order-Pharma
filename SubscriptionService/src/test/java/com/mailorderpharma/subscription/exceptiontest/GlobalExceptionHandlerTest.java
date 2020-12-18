package com.mailorderpharma.subscription.exceptiontest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.TokenValid;
import com.mailorderpharma.subscription.exceptions.GlobalExceptionHandler;
import com.mailorderpharma.subscription.exceptions.InvalidTokenException;
import com.mailorderpharma.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharma.subscription.restclients.AuthFeign;
import com.mailorderpharma.subscription.services.SubscriptionServiceImplementation;

@SpringBootTest
public class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	@Mock
	private AuthFeign authFeign;
	@Mock
	SubscriptionServiceImplementation subscriptionServiceImplementation;

	@Test
	public void invalidTokenException() {
		assertEquals(globalExceptionHandler.invalidTokenException(new InvalidTokenException("invalidTokenException"))
				.getStatusCode(), HttpStatus.UNAUTHORIZED);
	}

	@Test
	public void subscriptionListEmptyException() {
		assertEquals(
				globalExceptionHandler.subscriptionListEmptyException(
						new SubscriptionListEmptyException("SubscriptionListEmptyException")).getStatusCode(),
				HttpStatus.NOT_FOUND);
	}

	@Test
	public void serviceUnavailableException() {
		assertEquals(globalExceptionHandler.serviceUnavailableException().getStatus(), HttpStatus.SERVICE_UNAVAILABLE);
	}

}
