package com.mailorderpharma.subscription.services;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;

public interface SubscriptionService {
public ResponseEntity<?> subscribe(PrescriptionDetails prescriptionDetails);
public ResponseEntity<?> unsubscribe(Long mId,Long sId);
}
