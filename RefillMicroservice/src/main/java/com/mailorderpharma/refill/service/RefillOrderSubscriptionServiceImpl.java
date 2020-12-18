package com.mailorderpharma.refill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.dao.RefillOrderSubscriptionRepository;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.restclients.AuthFeign;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RefillOrderSubscriptionServiceImpl implements RefillOrderSubscriptionService {

	@Autowired
	RefillOrderSubscriptionRepository refillOrderSubscriptionRepository;

	@Autowired
	private AuthFeign authFeign;

	@Override
	public RefillOrderSubscription updateRefillOrderSubscription(long subId, String memberId, int quantity, int time,
			String token) throws InvalidTokenException {

		log.info("inside UpdateRefillOrderSubscription method");

		if (authFeign.getValidity(token).isValid()) {
			RefillOrderSubscription refillOrderSubscription = new RefillOrderSubscription();
			refillOrderSubscription.setSubscriptionId(subId);
			refillOrderSubscription.setRefillTime(time);
			refillOrderSubscription.setRefillQuantity(quantity);
			refillOrderSubscription.setMemberId(memberId);
			refillOrderSubscriptionRepository.save(refillOrderSubscription);

			return refillOrderSubscription;

		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public List<RefillOrderSubscription> getall(String token) throws InvalidTokenException {

		log.info("inside getall method");

		if (authFeign.getValidity(token).isValid()) {
			return refillOrderSubscriptionRepository.findAll();
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public void deleteBySubscriptionId(long subscriptionId, String token) throws InvalidTokenException {

		log.info("inside deleteBySubscriptionId method");

		if (authFeign.getValidity(token).isValid()) {
			refillOrderSubscriptionRepository.deleteBySubscriptionId(subscriptionId);
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

}
