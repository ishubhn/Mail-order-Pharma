package com.mailorderpharma.refill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.dao.RefillOrderSubscriptionRepository;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;

@Service
public class RefillOrderSubscriptionServiceImpl implements RefillOrderSubscriptionService {

	@Autowired
	RefillOrderSubscriptionRepository refillOrderSubscriptionRepository;

	@Override
	public RefillOrderSubscription UpdateRefillOrderSubscription(long Sub_id, String memberId, int quantity, int refillCycle) {

		RefillOrderSubscription refillOrderSubscription = new RefillOrderSubscription();
		refillOrderSubscription.setSubscriptionId(Sub_id);
		refillOrderSubscription.setRefillTime(refillCycle);
		refillOrderSubscription.setRefillQuantity(quantity);
		refillOrderSubscription.setMemberId(memberId);
		
		return refillOrderSubscriptionRepository.save(refillOrderSubscription);
	}

	@Override
	public List<RefillOrderSubscription> getall() {
		return refillOrderSubscriptionRepository.findAll();
	}

	

	@Override
	public void deleteBySubscriptionId(long subscriptionId) {
		System.out.println(refillOrderSubscriptionRepository.deleteBySubscriptionId(subscriptionId));

	}

}
