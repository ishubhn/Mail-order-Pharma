package com.mailorderpharma.refill.dao.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DrugQuantityNotAvailableException.class)
public class DrugQuantityNotAvailableException {
	
	@Test
	void drugQuantityNotAvailableException()
	{
		DrugQuantityNotAvailableException drugQuantityNotAvailableException = new DrugQuantityNotAvailableException();
		
	}

}
