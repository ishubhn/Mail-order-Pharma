package com.mailorderpharma.refill.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mailorderpharma.refill.dao.RefillOrderRepository;
import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.entity.TokenValid;
import com.mailorderpharma.refill.exception.DrugQuantityNotAvailable;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.exception.SubscriptionIdNotFoundException;
import com.mailorderpharma.refill.restclients.AuthFeign;
import com.mailorderpharma.refill.restclients.DrugDetail;
import com.mailorderpharma.refill.restclients.SubscriptionClient;
import com.mailorderpharma.refill.service.RefillOrderServiceImpl;
import com.mailorderpharma.refill.service.RefillOrderSubscriptionServiceImpl;

import feign.FeignException;


@SpringBootTest(classes = RefillOrderServiceImplTest.class)
public class RefillOrderServiceImplTest {
	
	private static final TokenValid TokenValid = null;

	@InjectMocks
	RefillOrderServiceImpl refillOrderServiceImpl;
	
	@Mock
	RefillOrderSubscriptionServiceImpl refillOrderSubscriptionService;

	@Mock
	DrugDetail drugDetailClient;

	@Mock
	SubscriptionClient subscriptionClient;
	
	@Mock
	RefillOrderRepository refillOrderRepository;
	
	@Mock
	private AuthFeign authFeign;

	@Test
	public void getStatus() throws SubscriptionIdNotFoundException, InvalidTokenException {
	
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn((ArrayList<RefillOrder>) list);
		refillOrderServiceImpl.getStatus(45, "token");

	} 
	@Test
	public void getStatusInvalidToken() throws SubscriptionIdNotFoundException, InvalidTokenException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.getStatus(45, "token") );
	}
	@Test
	public void getStatusInvalidSubscriptionIdNotFoundException() throws SubscriptionIdNotFoundException, InvalidTokenException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn((ArrayList<RefillOrder>) list);
		assertThrows(SubscriptionIdNotFoundException.class, ()->refillOrderServiceImpl.getStatus(54, "token") );
	}
	
	@Test
	public void getRefillDuesAsOfDate() throws SubscriptionIdNotFoundException, InvalidTokenException {
		ArrayList<RefillOrderSubscription> list = new ArrayList<>();
		RefillOrderSubscription refillOrder = new RefillOrderSubscription(1,2,"true",45,46);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderSubscriptionService.getall("token")).thenReturn((ArrayList<RefillOrderSubscription>) list);
	//	refillOrderServiceImpl.getRefillDuesAsOfDate("45", 45, "token");
		refillOrderServiceImpl.getRefillDuesAsOfDate("45", 45, "token");
	//	refillOrderServiceImpl.getRefillDuesAsOfDate("45", 47, "token");
	}
	@Test
	public void getRefillDuesAsOfDateInvalidToken() throws SubscriptionIdNotFoundException, InvalidTokenException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.getRefillDuesAsOfDate("45", 47, "token") );
	}
	
	@Test
	public void requestAdhocRefill() throws SubscriptionIdNotFoundException, InvalidTokenException, FeignException, ParseException, DrugQuantityNotAvailable {
		ArrayList<RefillOrderSubscription> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		
		ResponseEntity<String> entityname = new ResponseEntity<String>("45", HttpStatus.OK);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(subscriptionClient.getDrugNameBySubscriptionId((long) 45)).thenReturn(entityname);
		ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
		System.out.println(responseEntity.getStatusCodeValue());
		when(drugDetailClient.updateQuantity("45", "salem", 45)).thenReturn(responseEntity);
	//	when(refillOrderServiceImpl.requestAdhocRefill(45, true, 45, "salem", "token").responsevalue).then
		when(refillOrderRepository.save(refillOrder)).thenReturn(refillOrder);
		refillOrderServiceImpl.requestAdhocRefill(45,true,45, "salem", "token");
	//	assertThrows(NullPointerException.class,()->refillOrderServiceImpl.requestAdhocRefill(45,true,45, "location", "token")); 
	}
	
	@Test
	public void requestAdhocRefilllInvalidTokenException() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.requestAdhocRefill(45,true,45, "location", "token") );

	}
	
	
	@Test
	public void requestAdhocRefillDrugQuantityNotAvailable() throws SubscriptionIdNotFoundException, InvalidTokenException, FeignException, ParseException, DrugQuantityNotAvailable {
		ArrayList<RefillOrderSubscription> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		
		ResponseEntity<String> entityname = new ResponseEntity<String>("45", HttpStatus.OK);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(subscriptionClient.getDrugNameBySubscriptionId((long) 45)).thenReturn(entityname);
		ResponseEntity responseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
		System.out.println(responseEntity.getStatusCodeValue());
		when(drugDetailClient.updateQuantity("45", "salem", 45)).thenReturn(responseEntity);
	//	when(refillOrderServiceImpl.requestAdhocRefill(45, true, 45, "salem", "token").responsevalue).then
		when(refillOrderRepository.save(refillOrder)).thenReturn(refillOrder);
	//	refillOrderServiceImpl.requestAdhocRefill(45,true,45, "salem", "token");
		assertThrows(DrugQuantityNotAvailable.class,()->refillOrderServiceImpl.requestAdhocRefill(45,true,45, "salem", "token")); 
	}
	
	
	@Test
	public void requestRefill() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.save(refillOrder)).thenReturn(refillOrder);
		refillOrderServiceImpl.requestRefill(45, 45,"45", "token");
	}
	@Test
	public void requestRefillInvalidTokenException() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.requestRefill(45, 45,"45", "token") );

	}
	

	@Test
	public void getRefillDuesAsOfPayment() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),true,45,45,"54");
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn(list);
		refillOrderServiceImpl.getRefillDuesAsOfPayment(45, "token");
	}
	
	@Test
	public void getRefillDuesAsOfPaymentFalse() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrder> list = new ArrayList<>();
		RefillOrder refillOrder = new RefillOrder(1,new Date(),false,45,45,"54");
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderRepository.findAll()).thenReturn(list);
		refillOrderServiceImpl.getRefillDuesAsOfPayment(45, "token");
	}
	@Test
	public void getRefillDuesAsOfPaymentException() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.getRefillDuesAsOfPayment(45, "token") );

	}
	
	
	@Test
	public void UpdateRefill() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrderSubscription> list = new ArrayList<>();
		RefillOrderSubscription refillOrder = new RefillOrderSubscription(1,2,"true",45,1);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		when(refillOrderSubscriptionService.getall("token")).thenReturn(list);
		refillOrderServiceImpl.UpdateRefill("token");
	}
	
	@Test
	public void UpdateRefillException() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.UpdateRefill("token") );

	}
	@Test
	public void startTimer() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		ArrayList<RefillOrderSubscription> list = new ArrayList<>();
		RefillOrderSubscription refillOrder = new RefillOrderSubscription(1,2,"true",45,1);
		list.add(refillOrder);
		TokenValid response = new TokenValid("uid", "name", true) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		//when(refillOrderServiceImpl.startTimer("token").UpdateRefill("token")).thenReturn(list);
		refillOrderServiceImpl.startTimer("token");
	}
	
	@Test
	public void startTimerException() throws SubscriptionIdNotFoundException, InvalidTokenException, ParseException {
		TokenValid response = new TokenValid("uid", "name", false) ;
		when(authFeign.getValidity("token")).thenReturn(response);
		assertThrows(InvalidTokenException.class, ()->refillOrderServiceImpl.startTimer("token") );

	}
	
	

}
