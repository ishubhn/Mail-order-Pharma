package com.mailorderpharma.drugservice.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailorderpharma.drugservice.entity.Stock;

class StockTest {

	Date date = new Date();
	Stock stock1 = new Stock();
	Stock stock2 = new Stock("D1","Drug1",date,25);
	
	@Test
	void testDrugId() {
		stock1.setDrugId("D1");
		assertEquals("D1", stock1.getDrugId());
	}
	
	@Test
	void testDrugName() {
		stock1.setDrugName("Drug1");
		assertEquals("Drug1",stock1.getDrugName());
	}
	
	@Test
	void testDate() {
		stock1.setExpiryDate(date);
		assertEquals(date, stock1.getExpiryDate());
	}
	
	@Test
	void testStock() {
		stock1.setStock(25);
		assertEquals(25, stock1.getStock());
	}
}
