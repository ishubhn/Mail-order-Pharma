package com.mailorderpharma.refill.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mailorderpharma.refill.dao.RefillOrderRepository;
import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;
import com.mailorderpharma.refill.exception.DrugQuantityNotAvailable;
import com.mailorderpharma.refill.exception.InvalidTokenException;
import com.mailorderpharma.refill.exception.SubscriptionIdNotFoundException;
import com.mailorderpharma.refill.restclients.AuthFeign;
import com.mailorderpharma.refill.restclients.DrugDetailClient;
import com.mailorderpharma.refill.restclients.SubscriptionClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RefillOrderServiceImpl implements RefillOrderService {

	@Autowired
	public RefillOrderRepository refillOrderRepository;

	@Autowired
	RefillOrderSubscriptionServiceImpl refillOrderSubscriptionService;

	@Autowired
	DrugDetailClient drugDetailClient;

	@Autowired
	SubscriptionClient subscriptionClient;

	@Autowired
	private AuthFeign authFeign;

	@Override
	public List<RefillOrder> getStatus(long subId, String token)
			throws SubscriptionIdNotFoundException, InvalidTokenException {

		log.info("inside getStatus method");
		if (authFeign.getValidity(token).isValid()) {
			ArrayList<RefillOrder> list = new ArrayList<>();
			List<RefillOrder> finallist = null;
			try {
				list = (ArrayList<RefillOrder>) refillOrderRepository.findAll();
				finallist = list.stream().filter(p -> p.getSubId() == subId).collect(Collectors.toList());
				finallist.get(0);
			} catch (Exception ex) {
				throw new SubscriptionIdNotFoundException("Subscription ID is invalid");
			}
			return finallist;
		} else
			throw new InvalidTokenException("Invalid Credentials");

	}

	@Override
	public List<RefillOrderSubscription> getRefillDuesAsOfDate(String memberId, int date, String token)
			throws InvalidTokenException {

		log.info("inside getRefillDuesAsOfDate method");

		if (authFeign.getValidity(token).isValid()) {
			List<RefillOrderSubscription> list = refillOrderSubscriptionService.getall(token);

			List<RefillOrderSubscription> memberList = list.stream().filter(p -> p.getMemberId().equals(memberId))
					.collect(Collectors.toList());

			int userTime = date;

			List<RefillOrderSubscription> members = memberList.stream().filter(p -> userTime % p.getRefillTime() != 0)
					.collect(Collectors.toList());

			return members;
		} else
			throw new InvalidTokenException("Invalid Credentials");

	}

	@Override
	public RefillOrder requestAdhocRefill(Long subId, Boolean payStatus, int quantity, String location, String token)
			throws ParseException, FeignException, InvalidTokenException, DrugQuantityNotAvailable {

		log.info("inside requestAdhocRefill method");

		// add the name from subscription microservice pending

		if (authFeign.getValidity(token).isValid()) {
			ResponseEntity<String> entityname = subscriptionClient.getDrugNameBySubscriptionId(subId, token);

			String name = entityname.getBody();
			log.info("drugname ");
			
			//change this qs mark to appropriate type 
			ResponseEntity<?> responseEntity =  drugDetailClient.updateQuantity(token,
					name, location, quantity);
			log.info("updated");
			int responsevalue = responseEntity.getStatusCodeValue();
			log.info("staus val");
			if (responsevalue == 200) {
				// checking drug availability then if yes
				RefillOrder refillOrder = new RefillOrder();
				refillOrder.setSubId(subId);
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss");
				String str = format.format(date);
				refillOrder.setRefilledDate(format.parse(str));
				refillOrder.setQuantity(quantity);
				refillOrder.setPayStatus(payStatus);

				refillOrderRepository.save(refillOrder);
				log.info("refiloredr sabed");
				return refillOrder;
			} else {
				throw new DrugQuantityNotAvailable("DrugQuantityNotAvailable");

			}
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public RefillOrder requestRefill(long subId, int quantity, String memberId, String token)
			throws ParseException, InvalidTokenException {
		log.info("inside requestRefill method");

		if (authFeign.getValidity(token).isValid()) {
			RefillOrder refillOrder = new RefillOrder();
			refillOrder.setSubId(subId);
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			String str = format.format(date);
			refillOrder.setRefilledDate(format.parse(str));
			refillOrder.setQuantity(quantity);
			refillOrder.setPayStatus(true);
			refillOrder.setMemberId(memberId);
			refillOrderRepository.save(refillOrder);

			return refillOrder;
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public boolean getRefillDuesAsOfPayment(long subscriptionId, String token) throws InvalidTokenException {
		log.info("inside getRefillDuesAsOfPayment method");

		if (authFeign.getValidity(token).isValid()) {
			List<RefillOrder> list = refillOrderRepository.findAll();

			List<RefillOrder> paymentDueList = list.stream().filter(p -> p.getSubId() == subscriptionId)
					.filter(p -> (!p.getPayStatus())).collect(Collectors.toList());

			if (paymentDueList.size() == 0) {
				return true;
			}

			return false;
		} else
			throw new InvalidTokenException("Invalid Credentials");

	}

	@Override
	public String updateRefill(String token) throws InvalidTokenException {
		log.info("inside UpdateRefill method");

		if (authFeign.getValidity(token).isValid()) {

			List<RefillOrderSubscription> list = refillOrderSubscriptionService.getall(token);

			try {
				list.stream().forEach(

						l -> {
							Calendar cal = Calendar.getInstance(); // this is the method you should use, not the Date(),
																	// because it is desperated.
							int min = cal.get(Calendar.MINUTE);// get the hour number of the day, from 0 to 23
							if (min % l.getRefillTime() == 0) {

								try {
									requestRefill(l.getSubscriptionId(), l.getRefillQuantity(), l.getMemberId(), token);
								} catch (ParseException | InvalidTokenException e) {
								}
							}
						}

				);
			}

			catch (Exception e) {
				log.info("Exception inside UpdateRefill:");
			}

			return "sucess";
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public void startTimer(String token) throws InvalidTokenException {
		log.info("inside startTimer method");

		if (authFeign.getValidity(token).isValid()) {
			Timer timer = new Timer();
			TimerTask tt = new TimerTask() {
				public void run() {
					try {
						updateRefill(token);
					} catch (Exception e) {
						log.info("Exception inside StartTimer:");
					}
				}
			};
			timer.schedule(tt, 1000, 1000 * 60); // delay the task 1 second, and then run task every five seconds
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

}
