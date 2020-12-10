package com.mailorderpharma.subscription.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.subscription.Repository.PrescriptionRepository;
import com.mailorderpharma.subscription.Repository.SubscriptionRepository;
import com.mailorderpharma.subscription.entity.PrescriptionDetails;
import com.mailorderpharma.subscription.entity.SubscriptionDetails;
import com.mailorderpharma.subscription.restclients.DrugDetailClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImplementation implements SubscriptionService {

	@Autowired
	private DrugDetailClient drugDetailClient;
//	@Autowired
//	private RefillClient refillClient;

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
			SubscriptionDetails subscriptionDetails = new SubscriptionDetails(prescriptionDetails.getPrescriptionId(),
					prescriptionDetails.getMemberId(), LocalDate.now(), prescriptionDetails.getMemberLocation(),
					"active");
			log.info("subs obj created");
			subscriptionRepo.save(subscriptionDetails);
			log.info("subs obj saved");
		} catch (Exception e) {
			log.info("Inside catch unable to get drug details"+prescriptionDetails.getDrugName());
			return new ResponseEntity<>("Subscription cannot be accepted due to drug unavailability.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>("You have succesfully subscribed to - " + prescriptionDetails.getDrugName(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> unsubscribe(Long mId, Long sId) {

		return null;
	}

}
