package com.mailorderpharma.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class PrescriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PrescriptionId;
	private String memberId;
	private String memberLocation;
	private String policyNumber;
	private String insuranceProvider;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate prescriptionDate;
	private String drugName;
	private String dosageDefinition;
	private int quantity;
	private int courseDuration;
	private String doctorName;

}
