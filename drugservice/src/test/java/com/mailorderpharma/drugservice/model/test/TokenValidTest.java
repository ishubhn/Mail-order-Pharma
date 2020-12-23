package com.mailorderpharma.drugservice.model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailorderpharma.drugservice.entity.TokenValid;

class TokenValidTest {

	TokenValid token1 = new TokenValid();
	TokenValid token2 = new TokenValid("Uid","Name",true);
	
	@Test
	void testUid() {
		token1.setUid("Uid");
		assertEquals(token1.getUid(), "Uid");
	}

	@Test
	void testName() {
		token1.setName("Name");
		assertEquals(token1.getName(), "Name");
	}

	@Test
	void testIsValid() {
		token1.setValid(true);
		assertEquals(token1.isValid(), true);
	}
	
	@Test
	void testToString() {
		String str = token1.toString();
		assertEquals(token1.toString(), str);
	}

}
