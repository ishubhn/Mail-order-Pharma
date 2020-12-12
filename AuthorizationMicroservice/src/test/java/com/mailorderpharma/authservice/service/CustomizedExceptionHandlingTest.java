package com.mailorderpharma.authservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.mailorderpharma.authservice.service.CustomizedExceptionHandling;
import com.mailorderpharma.authservice.service.UnauthorizedException;

@SpringBootTest
public class CustomizedExceptionHandlingTest {
	
	@InjectMocks
	CustomizedExceptionHandling customizedExceptionHandling;
	
	@Mock
	UnauthorizedException unauthorizedException;
	
	
	
	@Test
	public void handleUnauthorizedExceptionsTest()
	{
		ResponseEntity<?> responseEntity= customizedExceptionHandling.handleUnauthorizedExceptions(unauthorizedException);
		System.out.println(responseEntity.getStatusCodeValue());
		assertEquals(400, responseEntity.getStatusCodeValue());
		
	}
	
	@Test
	public void handleNullPointerExceptions()
	{
		ResponseEntity<?> responseEntity= customizedExceptionHandling.handleNullPointerExceptions(null);
		System.out.println(responseEntity.getStatusCodeValue());
		assertEquals(400, responseEntity.getStatusCodeValue());
		
	}
	
	
	

}
