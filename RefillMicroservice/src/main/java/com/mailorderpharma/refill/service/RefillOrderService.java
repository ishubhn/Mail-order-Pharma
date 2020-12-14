package com.mailorderpharma.refill.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.exception.DrugQuantityNotAvailable;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.exception.SubscriptionIdNotFoundException;

import feign.FeignException;

@Service
public interface RefillOrderService {
	
	public List<RefillOrder> getStatus(long sub_id,String token) throws SubscriptionIdNotFoundException, InvalidTokenException;
	
	public List<RefillOrderSubscription> getRefillDuesAsOfDate(String memberId,int date,String token)throws InvalidTokenException;
	
	public boolean getRefillDuesAsOfPayment(long subscriptionId,String token)throws InvalidTokenException;
	
	public RefillOrder requestAdhocRefill(Long sub_id,Boolean pay_status,int quantity,String location,String token)
			throws ParseException, FeignException, InvalidTokenException, DrugQuantityNotAvailable;
	
	public RefillOrder requestRefill(long sub_id,int quantity,String memberId,String token) throws ParseException, InvalidTokenException;

	public  String UpdateRefill(String token) throws InvalidTokenException; 
	
	public void startTimer(String token)throws InvalidTokenException;

	
	
}
