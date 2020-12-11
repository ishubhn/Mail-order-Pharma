package com.mailorderpharma.subscription.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.subscription.Repository.PrescriptionRepository;
import com.mailorderpharma.subscription.Repository.SubscriptionRepository;
import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
import com.mailorderpharma.subscription.restclients.DrugDetailClient;
import com.mailorderpharma.subscription.restclients.RefillClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

	@Autowired
	private DrugDetailClient drugDetailClient;
	@Autowired
	private RefillClient refillClient;

	@Autowired
	PrescriptionRepository prescriptionRepo;
	@Autowired
	SubscriptionRepository subscriptionRepo;

	@Override
	public ResponseEntity<String> subscribe(PrescriptionDetails prescriptionDetails) {
		log.info("Inside subscribe service method");
		try {
			drugDetailClient.getDrugByName(prescriptionDetails.getDrugName());

			prescriptionDetails = prescriptionRepo.save(prescriptionDetails);
			log.info("prescription saved");

		} catch (Exception e) {
			log.info("Inside catch unable to get drug details" + prescriptionDetails.getDrugName());
			return new ResponseEntity<>("Subscription cannot be accepted due to drug unavailability.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		SubscriptionDetails subscriptionDetails = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
				prescriptionDetails.getCourseDuration(), prescriptionDetails.getQuantity(),
				prescriptionDetails.getMemberId(), LocalDate.now(), prescriptionDetails.getMemberLocation(), "active");

		log.info("subs obj created");
		subscriptionDetails=subscriptionRepo.save(subscriptionDetails);
		refillClient.requestRefillSubscription(subscriptionDetails.getSubscriptionId(),
				subscriptionDetails.getMemberId(), subscriptionDetails.getQuantity(),
				subscriptionDetails.getRefillCycle());

		
		log.info("subs obj saved");
		return new ResponseEntity<>("You have succesfully subscribed to - " + prescriptionDetails.getDrugName(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> unsubscribe(String mId, Long sId) {

		if (!refillClient.isPendingPaymentDues(sId)) {
			log.info("payment not clear");
			return new ResponseEntity<>("You have to clear your payment dues first.", HttpStatus.BAD_REQUEST);
		}
		subscriptionRepo.deleteById(sId);
		log.info("dleted "+sId);
		// inform refill service to stop refilling for this sId
		refillClient.deleteRefillData(sId);
		log.info("deleted refill success");
		return new ResponseEntity<>("You have succesfully Unsubscribed", HttpStatus.OK);
	}

	@Override
	public List<SubscriptionDetails> getAllSubscriptions(String mId) {
		log.info("get subscription for "+mId);
		return subscriptionRepo.findByMemberId(mId);
	}

}
