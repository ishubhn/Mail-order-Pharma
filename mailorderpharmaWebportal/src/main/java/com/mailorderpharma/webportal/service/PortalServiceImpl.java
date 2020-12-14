package com.mailorderpharma.webportal.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.mailorderpharma.webportal.entity.AdHocModel;
import com.mailorderpharma.webportal.entity.AuthResponse;
import com.mailorderpharma.webportal.entity.DateModel;
import com.mailorderpharma.webportal.entity.DrugDetails;
import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.RefillOrder;
import com.mailorderpharma.webportal.entity.RefillOrderSubscription;
import com.mailorderpharma.webportal.entity.SubscriptionDetails;
import com.mailorderpharma.webportal.exceptions.DrugQuantityNotAvailable;
import com.mailorderpharma.webportal.exceptions.InvalidTokenException;
import com.mailorderpharma.webportal.restclients.AuthClient;
import com.mailorderpharma.webportal.restclients.DrugClient;
import com.mailorderpharma.webportal.restclients.RefillClient;
import com.mailorderpharma.webportal.restclients.SubscriptionClient;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	AuthClient authClient;
	@Autowired
	SubscriptionClient subscriptionClient;
	@Autowired
	DrugClient drugClient;
	@Autowired
	RefillClient refillClient;

	private AuthResponse authResponse;

	@Override
	public String getWelcome(String token) {
		try {
			authResponse = authClient.getValidity(token);
		} catch (Exception e) {
			return "redirect:/";
		}
		return "welcome";

	}

	@Override
	public String getSupportedDrugs(HttpSession session, ModelMap modelMap) {
		try {
			String token = (String) session.getAttribute("token");
			System.out.println("1");
			authResponse = authClient.getValidity(token);
			log.info("2");
			List<DrugDetails> drugList = drugClient.getAllDrugs();
			System.out.println("3");
			modelMap.addAttribute("drugList", drugList);
			log.info("4");
		}

		catch (ExpiredJwtException e) {
			modelMap.addAttribute("Warning", "Please login again");
			return "redirect:/";
		} catch (NullPointerException e) {
			modelMap.addAttribute("Warning", "Null Pointer");
			return "redirect:/";
		}
		return "availabledrugs";

	}

	@Override
	public String subscribe(PrescriptionDetails prescriptionDetails, HttpSession session) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {
			return "session expired...Please login again";
		}
		prescriptionDetails.setMemberId((String) session.getAttribute("memberId"));
		String msg = "";
		try {
			msg = subscriptionClient.subscribe((String) session.getAttribute("token"), prescriptionDetails);
		} catch (Exception e) {
			log.info("in catch portalservice subscribe method " + e.getMessage());
			msg = "Drug is not available";
		}
		return msg;
	}

	@Override
	public List<SubscriptionDetails> getSubscriptions(HttpSession session) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {

		}

		return subscriptionClient.getAllSubscriptionsforMember((String) session.getAttribute("token"),
				(String) session.getAttribute("memberId"));
	}

	@Override
	public String unsubscribe(HttpSession session, Long sId) {
		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {

		}
		subscriptionClient.unsubscribe((String) session.getAttribute("token"),
				(String) session.getAttribute("memberId"), sId);

		return "redirect:/subscriptions";
	}

	@Override
	public List<RefillOrderSubscription> getRefillDueAsofDate(String token, HttpSession session, DateModel dateModel) {
		try {
			authResponse = authClient.getValidity(token);
		} catch (Exception e) {
		}
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		String str = format.format(dateModel.getDate());
		return refillClient.getRefillDuesAsOfDate((String) session.getAttribute("memberId"),
				Integer.parseInt(str.substring(8, 10))).getBody();
	}

	@Override
	public RefillOrder requestAdhocRefill(HttpSession session, AdHocModel adHocModel) throws NumberFormatException,
			FeignException, ParseException, InvalidTokenException, DrugQuantityNotAvailable {

		try {
			authResponse = authClient.getValidity((String) session.getAttribute("token"));
		} catch (Exception e) {
		}
//		return null;
		return refillClient.requestAdhocRefill((String) session.getAttribute("token"),
				((long) session.getAttribute("sub_Id")), adHocModel.isPaymentStatus(),
				adHocModel.getQuantity(), adHocModel.getLocation()).getBody();
	}

}
