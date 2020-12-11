package com.mailorderpharma.refill.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mailorderpharma.refill.entity.RefillOrder;
import com.mailorderpharma.refill.entity.RefillOrderSubscription;

@Service
public interface RefillOrderService {

	public List<RefillOrder> getStatus(long sub_id);

	public List<RefillOrderSubscription> getRefillDuesAsOfDate(String memberId, int date);

	public boolean getRefillPaymentDues(long subscriptionId);

	public RefillOrder requestAdhocRefill(long sub_id, Boolean pay_status, int quantity) throws ParseException;

	public RefillOrder requestRefill(long sub_id, int quantity,String mId) throws ParseException;

	public String UpdateRefill();

	public void startTimer();

}
