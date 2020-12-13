package com.mailorderpharma.webportal.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mailorderpharma.webportal.entity.PrescriptionDetails;
import com.mailorderpharma.webportal.entity.UserData;
import com.mailorderpharma.webportal.restclients.AuthClient;
import com.mailorderpharma.webportal.service.PortalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	AuthClient authclient;

	@Autowired
	private PortalService portalService;

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
		session.setAttribute("name", res.getUserid());
		return getWelcome(session);
	}

	public String getWelcome(HttpSession session) {
		return portalService.getWelcome((String) session.getAttribute("token"));
	}

	@GetMapping("/prescriptionform")
	public String getPrescriptionForm() {
		return "prescription";
	}

	@PostMapping("/subscribe")
	public ModelAndView subscribe(@ModelAttribute PrescriptionDetails prescriptionDetails, HttpSession session,
			BindingResult result) {
		log.info("inn subscribe post controller "+prescriptionDetails.toString());
		ModelAndView view= new ModelAndView("prescription");
		view.addObject("msg", portalService.subscribe(prescriptionDetails, session));
		return view;
	}
	@GetMapping("/subscriptions")
	public ModelAndView getAllSubscriptions(HttpSession session,Model model) {
		ModelAndView view= new ModelAndView("subscriptions");
		view.addObject("subscriptionList",portalService.getSubscriptions(session));
		return view;
	}
	
	@PostMapping("/unsubscribe/{sId}")
	public ModelAndView getAllSubsAfterUnsubscribe(@PathVariable Long sId,HttpSession session,Model model) {
		portalService.unsubscribe(session,sId);
		return getAllSubscriptions(session,model);
	}
}
