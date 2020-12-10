package com.mailorderpharma.subscription.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.services.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SubscriptionRestcontroller {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody PrescriptionDetails prescriptionDetails) {
		log.info("Inside subscribe controller method");
		return subscriptionService.subscribe(prescriptionDetails);
	}

	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<?> unsubscribe(@PathVariable("mId") Long memberId, @PathVariable("sId") Long subscriptionId) {
		log.info("Inside unsubscribe method");
		return null;
	}
}
