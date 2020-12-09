package com.mailorderpharma.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PrescriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PrescriptionId;
	private Long memberId;
	private String policyNumber;
	private String insuranceProvider;
	private LocalDate prescriptionDate;
	private Long drugId;
	private String dosageDefinition;
	private String prescriptionCourse;
	private String doctorName;

}
