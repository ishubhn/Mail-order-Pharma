package com.mailorderpharma.subscription.entity;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class SubscriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;
	private Long prescriptionId;
	private int refillCycle;
	private int quantity;
	private String memberId;
	private LocalDate subscriptionDate;
	private String memberLocation;
	private String subscriptionStatus;
	
	public SubscriptionDetails(Long prescriptionId, int refillCycle, int quantity, String memberId, LocalDate subscriptionDate, String memberLocation,
			String subscriptionStatus) {
		super();
		this.prescriptionId = prescriptionId;
		this.memberId = memberId;
		this.subscriptionDate = subscriptionDate;
		this.memberLocation = memberLocation;
		this.subscriptionStatus = subscriptionStatus;
		this.refillCycle=refillCycle;
		this.quantity=quantity;
	}
}
