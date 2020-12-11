package com.mailorderpharma.refill.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.service.RefillOrderService;
import com.mailorderpharma.refill.service.RefillOrderSubscriptionService;

@RestController
public class RefillController {

	@Autowired
	RefillOrderService refillOrderService;

	@Autowired
	RefillOrderSubscriptionService refillOrderSubscriptionService;

	@GetMapping("/viewRefillStatus/{sub_id}")
	public ResponseEntity<List<RefillOrder>> viewRefillStatus(@PathVariable("sub_id") long sub_id) {
		return ResponseEntity.ok().body(refillOrderService.getStatus(sub_id));
	}

	@GetMapping("/getRefillDuesAsOfDate/{memberId}/{date}")
	public ResponseEntity<List<RefillOrderSubscription>> getRefillDuesAsOfDate(
			@PathVariable("memberId") String memberId, @PathVariable("date") int date) {
		return ResponseEntity.ok().body(refillOrderService.getRefillDuesAsOfDate(memberId, date));
	}

	@GetMapping("/getRefillPaymentDues/{subscriptionId}")
	public ResponseEntity<Boolean> getRefillPaymentDues(@PathVariable("subscriptionId") long subscriptionId) {
		return ResponseEntity.ok().body(refillOrderService.getRefillPaymentDues(subscriptionId));
	}

	@PostMapping("/requestAdhocRefill/{sub_id}/{pay_status}/{quantity}")
	public ResponseEntity<RefillOrder> requestAdhocRefill(@PathVariable("sub_id") long sub_id,
			@PathVariable("pay_status") Boolean pay_status, @PathVariable("quantity") int quantity)
			throws ParseException {
		return ResponseEntity.accepted().body(refillOrderService.requestAdhocRefill(sub_id, pay_status, quantity));
	}

	@PostMapping("/requestRefillSubscription/{sub_id}/{memberId}/{quantity}/{refillCycle}")
	public ResponseEntity<RefillOrderSubscription> requestRefillSubscription(@PathVariable("sub_id") long sub_id,
			@PathVariable("memberId") String memberId, @PathVariable("quantity") int quantity,
			@PathVariable("refillCycle") int refillCycle) throws ParseException {
		return ResponseEntity.accepted()
				.body(refillOrderSubscriptionService.UpdateRefillOrderSubscription(sub_id, memberId, quantity, refillCycle));
	}

	@GetMapping("/viewRefillOrderSubscriptionStatus")
	public ResponseEntity<List<RefillOrderSubscription>> viewRefillOrderSubscriptionStatus() {
		return ResponseEntity.ok().body(refillOrderSubscriptionService.getall());
	}

	@GetMapping("/startTimer")
	public void startTimer() {
		refillOrderService.startTimer();
	}

	@DeleteMapping("/deleteBySubscriptionId/{subscriptionId}")
	public void deleteBySubscriptionId(@PathVariable("subscriptionId") Long subscriptionId) {
		refillOrderSubscriptionService.deleteBySubscriptionId(subscriptionId);
	}

}
