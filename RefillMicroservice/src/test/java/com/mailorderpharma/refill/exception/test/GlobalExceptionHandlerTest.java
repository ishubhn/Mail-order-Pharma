package com.mailorderpharma.refill.exception.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.ServiceUnavailableException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.mailorderpharma.refill.exception.DrugQuantityNotAvailable;
import com.mailorderpharma.refill.exception.GlobalExceptionHandler;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.exception.SubscriptionIdNotFoundException;

@SpringBootTest(classes = GlobalExceptionHandlerTest.class)
public class GlobalExceptionHandlerTest {
		
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	
//	@Mock
//	InvalidTokenException invalidtokenException;
//	
	@Test
	public void invalidTokenException()
	{
		assertEquals(globalExceptionHandler.invalidTokenException
				(new InvalidTokenException("invalidTokenException")).getStatusCode(),HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void subscriptionIdNotFoundException()
	{
		assertEquals(globalExceptionHandler.subscriptionIdNotFoundException
				(new SubscriptionIdNotFoundException("subscriptionIdNotFoundException")).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void serviceUnavailableException()
	{
		assertEquals(globalExceptionHandler.serviceUnavailableException
				(new ServiceUnavailableException("serviceUnavailableException")).getStatusCode(),HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@Test
	public void DrugQuantityNotAvailable()
	{
		assertEquals(globalExceptionHandler.DrugQuantityNotAvailable
				(new DrugQuantityNotAvailable("DrugQuantityNotAvailable")).getStatusCode(),HttpStatus.NOT_FOUND);
	}
	

}
