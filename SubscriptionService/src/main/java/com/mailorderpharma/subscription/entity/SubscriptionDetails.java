package com.mailorderpharma.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class SubscriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;
	private Long prescriptionId;
	private Long memberId;
	private LocalDate subscriptionDate;
	private String memberLocation;
	private String subscriptionStatus;
}
