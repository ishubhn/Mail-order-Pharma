package com.mailorderpharma.subscription.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;

public interface SubscriptionService {
public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails);
public ResponseEntity<String> unsubscribe(String mId,Long sId);
public List<SubscriptionDetails> getAllSubscriptions(String mId);
}
