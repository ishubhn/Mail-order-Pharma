package com.mailorderpharma.webportal.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.SubscriptionDetails;
public interface PortalService {

	String getWelcome(String attribute);

	String subscribe(PrescriptionDetails prescriptionDetails,HttpSession session);

	List<SubscriptionDetails> getSubscriptions(HttpSession session);

	void unsubscribe(HttpSession session, Long sId);

}
