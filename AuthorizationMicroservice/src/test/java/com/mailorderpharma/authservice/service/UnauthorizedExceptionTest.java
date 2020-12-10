package com.mailorderpharma.authservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailorderpharma.authservice.service.UnauthorizedException;

@SpringBootTest
public class UnauthorizedExceptionTest {
	
	
	
	@Test
	public void constructortest()
	{
		UnauthorizedException unauthorizedException =new UnauthorizedException("unauthorized");
		//System.out.println(unauthorizedException.getMessage());
		assertEquals("unauthorized", unauthorizedException.getMessage());
	}

}
