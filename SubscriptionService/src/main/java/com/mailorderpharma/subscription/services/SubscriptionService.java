package com.mailorderpharma.subscription.services;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;

public interface SubscriptionService {
public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails);
public ResponseEntity<String> unsubscribe(Long mId,Long sId);
}
