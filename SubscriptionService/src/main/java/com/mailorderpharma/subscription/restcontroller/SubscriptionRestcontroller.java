package com.mailorderpharma.subscription.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SubscriptionRestcontroller {

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestBody PrescriptionDetails prescriptionDetails) {
		log.info("Inside subscribe method");

		return null;
	}

	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<?> unsubscribe(@PathVariable("mId") Long memberId, @PathVariable("sId") Long subscriptionId) {
		log.info("Inside unsubscribe method");
		return null;
	}
}
