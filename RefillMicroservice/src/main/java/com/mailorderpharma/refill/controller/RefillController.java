package com.mailorderpharma.refill.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.exception.DrugQuantityNotAvailable;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.exception.SubscriptionIdNotFoundException;
import com.mailorderpharma.refill.service.RefillOrderService;
import com.mailorderpharma.refill.service.RefillOrderSubscriptionService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RefillController {

	@Autowired 
	public RefillOrderService service;

	@Autowired
	RefillOrderSubscriptionService refillOrderSubscriptionService;

	@RequestMapping(path = "/viewRefillStatus/{sub_id}", method = RequestMethod.GET)
	public ResponseEntity<List<RefillOrder>> viewRefillStatus(@RequestHeader("Authorization") String token,
			@PathVariable("sub_id") long sub_id) throws SubscriptionIdNotFoundException, InvalidTokenException {
		log.info("Inside Refill Controller viewRefillStatus method");
		return ResponseEntity.ok().body(service.getStatus(sub_id, token));
	}

	@RequestMapping(path = "/getRefillDuesAsOfDate/{memberId}/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<RefillOrderSubscription>> getRefillDuesAsOfDate(
			@RequestHeader("Authorization") String token, @PathVariable("memberId") String memberId,
			@PathVariable("date") int date) throws InvalidTokenException {
		log.info("Inside Refill Controller getRefillDuesAsOfDate method");
		return ResponseEntity.ok().body(service.getRefillDuesAsOfDate(memberId, date, token));
	}

	@RequestMapping(path = "/getRefillDuesAsOfPayment/{subscriptionId}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> getRefillDuesAsOfPayment(@RequestHeader("Authorization") String token,
			@PathVariable("subscriptionId") long subscriptionId) throws InvalidTokenException {
		log.info("Inside Refill Controller getRefillDuesAsOfDate method");

		return ResponseEntity.ok().body(service.getRefillDuesAsOfPayment(subscriptionId, token));
	}

	@RequestMapping(path = "/requestAdhocRefill/{sub_id}/{pay_status}/{quantity}/{location}", method = RequestMethod.POST)
	public ResponseEntity<RefillOrder> requestAdhocRefill(@RequestHeader("Authorization") String token,
			@PathVariable("sub_id") Long i, @PathVariable("pay_status") Boolean pay_status,
			@PathVariable("quantity") int quantity, @PathVariable("location") String location)
			throws ParseException, FeignException, InvalidTokenException, DrugQuantityNotAvailable {
		log.info("Inside Refill Controller requestAdhocRefill method");

		return ResponseEntity.accepted()
				.body(service.requestAdhocRefill(i, pay_status, quantity, location, token));
	}

	@RequestMapping(path = "/requestRefillSubscription/{sub_id}/{memberId}/{quantity}/{time}", method = RequestMethod.POST)
	public ResponseEntity<RefillOrderSubscription> requestRefillSubscription(
			@RequestHeader("Authorization") String token, @PathVariable("sub_id") long sub_id,
			@PathVariable("memberId") String memberId, @PathVariable("quantity") int quantity,
			@PathVariable("time") int time) throws ParseException, InvalidTokenException {
		log.info("Inside Refill Controller requestRefillSubscription method");
		return ResponseEntity.accepted().body(
				refillOrderSubscriptionService.UpdateRefillOrderSubscription(sub_id, memberId, quantity, time, token));
	}

	@RequestMapping(path = "/viewRefillOrderSubscriptionStatus", method = RequestMethod.GET)
	public ResponseEntity<List<RefillOrderSubscription>> viewRefillOrderSubscriptionStatus(
			@RequestHeader("Authorization") String token) throws InvalidTokenException {
		log.info("Inside Refill Controller viewRefillOrderSubscriptionStatus method");

		return ResponseEntity.ok().body(refillOrderSubscriptionService.getall(token));
	}

	@RequestMapping(path = "/startTimer", method = RequestMethod.GET)
	public void startTimer(@RequestHeader("Authorization") String token) throws InvalidTokenException {
		log.info("Inside Refill Controller startTimer method");

		service.startTimer(token);
	}

	@RequestMapping(path = "/deleteBySubscriptionId/{subscriptionId}", method = RequestMethod.DELETE)
	public void deleteBySubscriptionId(@RequestHeader("Authorization") String token,
			@PathVariable("subscriptionId") long subscriptionId) throws InvalidTokenException {
		log.info("Inside Refill Controller deleteBySubscriptionId method");

		refillOrderSubscriptionService.deleteBySubscriptionId(subscriptionId, token);
	}

}
