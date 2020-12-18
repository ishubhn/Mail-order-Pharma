package com.mailorderpharma.subscription.restcontroller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
import com.mailorderpharma.subscription.exceptions.InvalidTokenException;
import com.mailorderpharma.subscription.services.SubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SubscriptionRestcontroller {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(@RequestHeader("Authorization") String token,
			@RequestBody PrescriptionDetails prescriptionDetails)
			throws InvalidTokenException, ConstraintViolationException {
		log.info("Inside subscribe controller method");
		return subscriptionService.subscribe(prescriptionDetails, token);
	}

	@PostMapping("/unsubscribe/{mId}/{sId}")
	public ResponseEntity<?> unsubscribe(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String memberId, @PathVariable("sId") Long subscriptionId)
			throws InvalidTokenException {
		log.info("Inside unsubscribe method");
		return subscriptionService.unsubscribe(memberId, subscriptionId, token);
	}

	@GetMapping("/getAllSubscriptions/{mId}")
	public List<SubscriptionDetails> getAllSubscriptionsforMember(@RequestHeader("Authorization") String token,
			@PathVariable("mId") String mId) throws InvalidTokenException {
		return subscriptionService.getAllSubscriptions(mId, token);
	}

	@GetMapping("/getDrugName/{sId}")
	public ResponseEntity<String> getDrugNameBySubscriptionId(@RequestHeader("Authorization") String token,
			@PathVariable("sId") Long sId) throws InvalidTokenException {
		return subscriptionService.getDrugNameBySubscriptionId(sId, token);
	}
}
