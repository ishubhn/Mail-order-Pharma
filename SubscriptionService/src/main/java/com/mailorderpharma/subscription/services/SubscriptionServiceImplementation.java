package com.mailorderpharma.subscription.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.subscription.Repository.PrescriptionRepository;
import com.mailorderpharma.subscription.Repository.SubscriptionRepository;
import com.mailorderpharma.subscription.entity.DrugDetails;
import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
import com.mailorderpharma.subscription.exceptions.InvalidTokenException;
import com.mailorderpharma.subscription.exceptions.SubscriptionListEmptyException;
import com.mailorderpharma.subscription.restclients.AuthFeign;
import com.mailorderpharma.subscription.restclients.DrugDetailClient;
import com.mailorderpharma.subscription.restclients.RefillClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

	@Autowired
	private DrugDetailClient drugDetailClient;
	@Autowired
	private RefillClient refillClient;

	@Autowired
	private AuthFeign authFeign;

	@Autowired
	PrescriptionRepository prescriptionRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;

	@Override
	public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails, String token)
			throws InvalidTokenException, FeignException {
		log.info("Inside subscribe service method");
		if (authFeign.getValidity(token).getBody().isValid()) {
			log.info("subscribe service method- token is valid");

			drugDetailClient.getDrugByName(token, prescriptionDetails.getDrugName());

			prescriptionDetails = prescriptionRepo.save(prescriptionDetails);
			log.info("prescription saved");

			SubscriptionDetails subscriptionDetails = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
					prescriptionDetails.getCourseDuration(), prescriptionDetails.getQuantity(),
					prescriptionDetails.getMemberId(), LocalDate.now(), prescriptionDetails.getMemberLocation(),
					"active");

			log.info("subs obj created");
			subscriptionDetails = subscriptionRepo.save(subscriptionDetails);
			refillClient.requestRefillSubscription(subscriptionDetails.getSubscriptionId(),
					subscriptionDetails.getMemberId(), subscriptionDetails.getQuantity(),
					subscriptionDetails.getRefillCycle());

			log.info("subs obj saved");
		} else
			throw new InvalidTokenException("Invalid Credentials");

		return new ResponseEntity<>("You have succesfully subscribed to - " + prescriptionDetails.getDrugName(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> unsubscribe(String mId, Long sId, String token)
			throws InvalidTokenException, FeignException {

		if (authFeign.getValidity(token).getBody().isValid()) {
			if (!refillClient.isPendingPaymentDues(sId)) {
				log.info("payment not clear");
				return new ResponseEntity<>("You have to clear your payment dues first.", HttpStatus.BAD_REQUEST);
			}
			subscriptionRepo.deleteById(sId);
			log.info("dleted " + sId);
			// inform refill service to stop refilling for this sId
			refillClient.deleteRefillData(sId);
			log.info("delete refill success");
		} else
			throw new InvalidTokenException("Invalid Credentials");

		return new ResponseEntity<>("You have succesfully Unsubscribed", HttpStatus.OK);
	}

	@Override
	public List<SubscriptionDetails> getAllSubscriptions(String mId, String token)
			throws InvalidTokenException, SubscriptionListEmptyException {
		log.info("get subscription for " + mId);
		if (authFeign.getValidity(token).getBody().isValid()) {
			if (subscriptionRepo.findByMemberId(mId).isEmpty())
				throw new SubscriptionListEmptyException("Currently you do not have any subscriptions");
			return subscriptionRepo.findByMemberId(mId);
		} else
			throw new InvalidTokenException("Invalid Credentials");

	}

	@Override
	public ResponseEntity<String> getDrugNameBySubscriptionId(Long sId, String token) throws InvalidTokenException {
		log.info("getDrugNameBySubscriptionId -" + sId);
		if (authFeign.getValidity(token).getBody().isValid()) {
			Long pId = subscriptionRepo.findById(sId).orElseThrow().getPrescriptionId();
			log.info("getDrugNameBySubscriptionId pId-" + pId);
			String drugName = prescriptionRepo.findById(pId).orElseThrow().getDrugName();
			return new ResponseEntity<>(drugName, HttpStatus.OK);
		} else
			throw new InvalidTokenException("Invalid Credentials");

	}

}
