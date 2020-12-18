package com.mailorderpharma.subscription.entitytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.mailorderpharma.subscription.entity.ConstraintErrorResponse;

class ConstraintErrorResponseTest {

	LocalDateTime date = LocalDateTime.now();
	List<String> list = new ArrayList<>();
	ConstraintErrorResponse response1 = new ConstraintErrorResponse(HttpStatus.OK, date, list);
	ConstraintErrorResponse response2 = new ConstraintErrorResponse();
	
	@Test
	void test() {
		
		response2.setHttpStatus(response1.getHttpStatus());
		response2.setLocalDateTime(response1.getLocalDateTime());
		response2.setMessage(response1.getMessage());
		
		assertEquals(response2.getHttpStatus(), response1.getHttpStatus());
		assertEquals(response2.getLocalDateTime(), response1.getLocalDateTime());
		assertEquals(response2.getMessage(), response1.getMessage());
	}

}
