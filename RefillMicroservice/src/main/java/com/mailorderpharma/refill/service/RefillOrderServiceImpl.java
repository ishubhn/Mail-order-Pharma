package com.mailorderpharma.refill.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.dao.RefillOrderRepository;
import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;

@Service
public class RefillOrderServiceImpl implements RefillOrderService {

	@Autowired
	RefillOrderRepository refillOrderRepository;

	@Autowired
	RefillOrderSubscriptionService refillOrderSubscriptionService;

	
	@Override
	public void startTimer() {
		Timer timer = new Timer();
		TimerTask tt = new TimerTask() {
			public void run() {
				try {
					UpdateRefill();
				} catch (Exception e) {
					System.out.println("Exception inside StartTimer: " + e);
				}
			}
		};
		timer.schedule(tt, 1000, 1000 * 60); // delay the task 1 second, and then run task every five seconds

	}
	@Override
	public String UpdateRefill() {

		List<RefillOrderSubscription> list = refillOrderSubscriptionService.getall();

		try {
			list.stream().forEach(

					l -> {
						Calendar cal = Calendar.getInstance(); // this is the method you should use, not the Date(),
																// because it is desperated.
						int min = cal.get(Calendar.MINUTE);// get the hour number of the day, from 0 to 23
						if (min % l.getRefillTime() == 0) {

							try {
								requestRefill(l.getSubscriptionId(), l.getRefillQuantity(),l.getMemberId());
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
					}

			);
		}

		catch (Exception e) {
			System.out.println("Exception inside UpdateRefill:" + e);
		}

		return "sucess";
	}
	@Override
	public List<RefillOrder> getStatus(long sub_id) {

		List<RefillOrder> refillOrderList = refillOrderRepository.findAll();

		refillOrderList = refillOrderList.stream().filter(p -> p.getSub_id() == sub_id).collect(Collectors.toList());

		return refillOrderList;
	}

	@Override
	public List<RefillOrderSubscription> getRefillDuesAsOfDate(String memberId, int date) {

		List<RefillOrderSubscription> list = refillOrderSubscriptionService.getall();

		List<RefillOrderSubscription> memberList = list.stream().filter(p -> p.getMemberId().equals(memberId))
				.collect(Collectors.toList());

		int userTime = date;

		List<RefillOrderSubscription> members = memberList.stream().filter(p -> userTime % p.getRefillTime() == 0
				|| userTime % p.getRefillTime() == 1 || userTime % p.getRefillTime() == p.getRefillTime() - 1)
				.collect(Collectors.toList());

		return members;

	}

	@Override
	public RefillOrder requestAdhocRefill(long sub_id, Boolean pay_status, int quantity) throws ParseException {

		// checking drug availability then if yes
		RefillOrder refillOrder = new RefillOrder();
		refillOrder.setSub_id(sub_id);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss");
		String str = format.format(date);
		refillOrder.setRefilledDate(format.parse(str));
		refillOrder.setQuantity(quantity);
		refillOrder.setPay_status(pay_status);

		refillOrderRepository.save(refillOrder);

		return refillOrder;
	}

	@Override
	public RefillOrder requestRefill(long sub_id, int quantity,String mId) throws ParseException {

		RefillOrder refillOrder = new RefillOrder();
		refillOrder.setSub_id(sub_id);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String str = format.format(date);
		refillOrder.setRefilledDate(format.parse(str));
		refillOrder.setQuantity(quantity);
		refillOrder.setPay_status(true);
		refillOrder.setMemberId(mId);
		refillOrderRepository.save(refillOrder);

		return refillOrder;
	}

	@Override
	public boolean getRefillPaymentDues(long subscriptionId) {

		List<RefillOrder> list = refillOrderRepository.findAll();

		List<RefillOrder> paymentDueList = list.stream().filter(p -> p.getSub_id() == subscriptionId)
				.filter(p -> (!p.getPay_status())).collect(Collectors.toList());

		if (paymentDueList.size() == 0) {
			return true;
		}

		return false;

	}

}
