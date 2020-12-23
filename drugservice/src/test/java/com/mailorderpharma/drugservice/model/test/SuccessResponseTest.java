package com.mailorderpharma.drugservice.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mailorderpharma.drugservice.entity.SuccessResponse;

class SuccessResponseTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void test() {
		SuccessResponse successResponseOne = new SuccessResponse();
		successResponseOne.setResponseMessage("Success");
		assertEquals("Success", successResponseOne.getResponseMessage());
	}
	
	@Test
	public void testAllArgs() {
		SuccessResponse successResponseOne = new SuccessResponse("Failure");
		assertEquals("Failure", successResponseOne.getResponseMessage());
	}

}
