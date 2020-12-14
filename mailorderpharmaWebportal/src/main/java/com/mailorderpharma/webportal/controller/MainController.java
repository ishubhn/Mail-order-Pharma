package com.mailorderpharma.webportal.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mailorderpharma.webportal.entity.AdHocModel;
import com.mailorderpharma.webportal.entity.DateModel;
import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.UserData;
import com.mailorderpharma.webportal.exceptions.DrugQuantityNotAvailable;
import com.mailorderpharma.webportal.exceptions.InvalidTokenException;
import com.mailorderpharma.webportal.restclients.AuthClient;
import com.mailorderpharma.webportal.service.PortalService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	AuthClient authclient;

	@Autowired
	private PortalService portalService;

	/* Login end-points------------------------------------ */

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String postLogin(@ModelAttribute UserData user, HttpSession session) {
		log.info("inn login post" + user.toString());

		UserData res = null;
		try {
			res = authclient.login(user);
			log.info("inn login post login success");
		} catch (Exception e) {
			return "login";
		}
		session.setAttribute("token", "Bearer " + res.getAuthToken());
		session.setAttribute("memberId", res.getUserid());
		return getWelcome(session);
	}

	@GetMapping("/home")
	public String getWelcome(HttpSession session) {
		return portalService.getWelcome((String) session.getAttribute("token"));
	}

	/* DrugService end-points--------------------- */

	@GetMapping("/supportedDrugs")
	public String getSupportedDrugs(HttpSession session, ModelMap warning) {
		return postSupportedDrugs(session, warning);
	}

	@PostMapping(path = "/supportedDrugs")
	public String postSupportedDrugs(HttpSession session, ModelMap warning) {
		log.info("Controller supportedDrugs");
		return portalService.getSupportedDrugs(session, warning);
	}

	/* Subscription end-points--------------------- */

	@GetMapping("/prescriptionform")
	public String getPrescriptionForm() {
		return "prescription";
	}

	@PostMapping("/subscribe")
	public ModelAndView subscribe(@ModelAttribute PrescriptionDetails prescriptionDetails, HttpSession session,
			BindingResult result) {
		log.info("inn subscribe post controller " + prescriptionDetails.toString());
		ModelAndView view = new ModelAndView("prescription");
		view.addObject("msg", portalService.subscribe(prescriptionDetails, session));
		return view;
	}

	@GetMapping("/subscriptions")
	public ModelAndView getAllSubscriptions(HttpSession session, Model model) {
		ModelAndView view = new ModelAndView("subscriptions");
		view.addObject("subscriptionList", portalService.getSubscriptions(session));
		return view;
	}

	@PostMapping("/unsubscribe/{sId}")
	public String getAllSubsAfterUnsubscribe(@PathVariable Long sId, HttpSession session, Model model) {

		return portalService.unsubscribe(session, sId);
	}
	/* Refill end-points-------------------- */

	@PostMapping("/refillDueAsOfDate")
	public ModelAndView getRefillDueAsofDate(@ModelAttribute DateModel dateModel, HttpSession session) {
		ModelAndView view = new ModelAndView("refillDueAsofDate");
		view.addObject("refillDues",
				portalService.getRefillDueAsofDate((String) session.getAttribute("token"), session, dateModel));
		return view;
	}

	@GetMapping("/adhocRefill/{sId}")
	public ModelAndView adhocRefill(@PathVariable Long sId, HttpSession session) {
		ModelAndView view = new ModelAndView("adhocRefill");
		view.addObject("subId", sId);
		log.info("in controller get adhocrefill");
		session.setAttribute("sub_Id", sId);
		return view;
	}

	@PostMapping("/postAdhocRefill")
	public ModelAndView postAdHocDetails( @ModelAttribute AdHocModel adHocModel, HttpSession session,BindingResult result)
			throws NumberFormatException, FeignException, ParseException, InvalidTokenException,
			DrugQuantityNotAvailable {
		log.info("in postAdHocDetails controller "+adHocModel.getLocation()+adHocModel.isPaymentStatus()+adHocModel.getQuantity());
		ModelAndView view = new ModelAndView("blank");
		portalService.requestAdhocRefill( session, adHocModel);
		return view;
	}
}
