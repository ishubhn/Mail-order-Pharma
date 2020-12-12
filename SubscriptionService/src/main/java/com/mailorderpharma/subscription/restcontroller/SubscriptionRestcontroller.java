package com.mailorderpharma.subscription.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
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
	public ResponseEntity<?> unsubscribe(@PathVariable("mId") String memberId, @PathVariable("sId") Long subscriptionId) {
		log.info("Inside unsubscribe method");
		subscriptionService.unsubscribe(memberId, subscriptionId);
		return null;
	}
	
	@GetMapping("/getAllSubscriptions/{mId}")
	public List<SubscriptionDetails> getAllSubscriptionsforMember(@PathVariable("mId")String mId){
		return subscriptionService.getAllSubscriptions(mId);
	}
	
	@GetMapping("/getDrugName/{sId}")
	public ResponseEntity<String> getDrugNameBySubscriptionId(@PathVariable("sId") Long sId){
		return subscriptionService.getDrugNameBySubscriptionId(sId);
	}
}
