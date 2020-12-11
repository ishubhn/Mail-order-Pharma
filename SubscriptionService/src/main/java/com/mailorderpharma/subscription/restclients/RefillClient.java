package com.mailorderpharma.subscription.restclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8454/refillapi", name = "refillapp")
public interface RefillClient {

	@GetMapping("/getRefillPaymentDues/{sub_id}")
	public Boolean isPendingPaymentDues(@PathVariable("sub_id") Long sId);

	@PostMapping("/requestRefillSubscription/{sub_id}/{memberId}/{quantity}/{refillCycle}")
	public ResponseEntity<?> requestRefillSubscription(@PathVariable("sub_id") Long sub_id,
			@PathVariable("memberId") String memberId, @PathVariable("quantity") int quantity,
			@PathVariable("refillCycle") int refillCycle);
	
	@DeleteMapping("/deleteBySubscriptionId/{subscriptionId}")
	public void deleteRefillData(@PathVariable("subscriptionId")Long subscriptionId);
}
