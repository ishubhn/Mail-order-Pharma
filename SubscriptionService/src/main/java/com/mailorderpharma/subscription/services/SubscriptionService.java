package com.mailorderpharma.subscription.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
import com.mailorderpharma.subscription.exceptions.InvalidTokenException;

public interface SubscriptionService {
public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails,String token) throws InvalidTokenException;
public ResponseEntity<String> unsubscribe(String mId,Long sId,String token) throws InvalidTokenException;
public List<SubscriptionDetails> getAllSubscriptions(String mId,String token) throws InvalidTokenException;
ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException;
}
