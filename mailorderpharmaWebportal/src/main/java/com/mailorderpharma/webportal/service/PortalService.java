package com.mailorderpharma.webportal.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

import com.mailorderpharma.webportal.entity.AdHocModel;
import com.mailorderpharma.webportal.entity.DateModel;
import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.RefillOrder;
import com.mailorderpharma.webportal.entity.RefillOrderSubscription;
import com.mailorderpharma.webportal.entity.SubscriptionDetails;
import com.mailorderpharma.webportal.exceptions.DrugQuantityNotAvailable;
import com.mailorderpharma.webportal.exceptions.InvalidTokenException;

import feign.FeignException;

public interface PortalService {

	String getWelcome(String attribute);

	String subscribe(PrescriptionDetails prescriptionDetails, HttpSession session);

	List<SubscriptionDetails> getSubscriptions(HttpSession session);

	String unsubscribe(HttpSession session, Long sId);

	String getSupportedDrugs(HttpSession session, ModelMap modelMap);

	List<RefillOrderSubscription> getRefillDueAsofDate(String token, HttpSession session, DateModel dateModel);

	RefillOrder requestAdhocRefill(HttpSession session, AdHocModel adHocModel) throws NumberFormatException,
			FeignException, ParseException, InvalidTokenException, DrugQuantityNotAvailable;
}
