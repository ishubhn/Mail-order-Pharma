package com.mailorderpharma.refill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.entity.RefillOrderSubscription;

@Service
public interface RefillOrderSubscriptionService {
	
	public  RefillOrderSubscription UpdateRefillOrderSubscription(long Sub_id,String memberId,int quantity ,int time);
	
	public List<RefillOrderSubscription> getall(); 
	
	public void deleteBySubscriptionId(long subscriptionId);

}
