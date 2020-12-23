package com.mailorderpharma.subscription.exceptiontest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.ServiceUnavailableException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.mailorderpharma.subscription.exceptions.GlobalExceptionHandler;
import com.mailorderpharma.subscription.exceptions.InvalidTokenException;
import com.mailorderpharma.subscription.exceptions.SubscriptionListEmptyException;

@SpringBootTest
public class GlobalExceptionHandlerTest {

	
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	
	@Test
	public void invalidTokenException()
	{
		assertEquals(globalExceptionHandler.invalidTokenException
				(new InvalidTokenException("invalidTokenException")).getStatusCode(),HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void subscriptionListEmptyException()
	{
		assertEquals(globalExceptionHandler.subscriptionListEmptyException
				(new SubscriptionListEmptyException("SubscriptionListEmptyException")).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void serviceUnavailableException()
	{
		assertEquals(globalExceptionHandler.serviceUnavailableException
				().getStatus(),HttpStatus.SERVICE_UNAVAILABLE);
	}
}
