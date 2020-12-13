package com.mailorderpharma.webportal.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.webportal.entity.AuthResponse;
import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.SubscriptionDetails;
import com.mailorderpharma.webportal.restclients.AuthClient;
import com.mailorderpharma.webportal.restclients.SubscriptionClient;

@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	AuthClient authClient;
	@Autowired
	SubscriptionClient subscriptionClient;
	private AuthResponse authResponse;

	@Override
	public String getWelcome(String token) {
		try {
			authResponse = authClient.getValidity(token);
		} catch (Exception e) {
			return "redirect:/login";
		}
		return "welcome";

	}

	@Override
	public String subscribe(PrescriptionDetails prescriptionDetails, HttpSession session) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {
			return "redirect:/login";
		}
		prescriptionDetails.setMemberId((String) session.getId());
		return subscriptionClient.subscribe((String) session.getAttribute("token"), prescriptionDetails);

	}

	@Override
	public List<SubscriptionDetails> getSubscriptions(HttpSession session) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {

		}

		return subscriptionClient.getAllSubscriptionsforMember((String) session.getAttribute("token"),
				(String) session.getId());
	}

	@Override
	public void unsubscribe(HttpSession session, Long sId) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {

		}
		subscriptionClient.unsubscribe((String) session.getAttribute("token"), (String) session.getId(), sId);
	}

}
