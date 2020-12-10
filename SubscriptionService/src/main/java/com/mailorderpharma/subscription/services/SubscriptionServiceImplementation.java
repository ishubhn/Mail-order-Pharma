package com.mailorderpharma.subscription.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.restclients.DrugDetailClient;
import com.mailorderpharma.subscription.restclients.RefillClient;

public class SubscriptionServiceImplementation implements SubscriptionService{
	
	@Autowired
	private DrugDetailClient drugDetailClient;
	@Autowired
	private RefillClient refillClient;

	@Override
	public ResponseEntity<?> subscribe(PrescriptionDetails prescriptionDetails) {
		return null;
	}

	@Override
	public ResponseEntity<?> unsubscribe(Long mId, Long sId) {
		
		return null;
	}

}
