package com.mailorderpharma.subscription.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PrescriptionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long prescriptionId;
	
	private String memberId;
	@NotEmpty(message = "Location cannot be empty")
	@Pattern(regexp = "[A-z]+",message = "Location must contain only Alphabets")
	@Size(min = 1,max = 20)
	private String memberLocation;
	
	@NotEmpty(message = "PolicyNumber Name canot be empty")
	@Pattern(regexp = "[A-z0-9]+",message = "PolicyNumber must contain only Numbers or Alphabets or Combination of Both")
	@Size(min = 1,max = 20)
	private String policyNumber;
	
	@NotEmpty(message = "Insurance Provider cannot be empty")
	@Pattern(regexp = "[a-zA-Z]+",message = "Insurance Provider must contain only Alphabets")
	@Size(min = 1,max = 20)
	private String insuranceProvider;
	
	private LocalDate prescriptionDate;
	
	@NotEmpty(message = "DrugName cannot be empty")
	@Pattern(regexp = "[A-z0-9]+",message = "DrugName must contain only Numbers or Alphabets or Combination of Both")
	@Size(min = 1,max = 20)
	private String drugName;
	
	
	private String dosageDefinition;
	
	@Min(1)
	@Max(100)
	private int quantity;
	
	@Min(1)
	@Max(15)
	private int courseDuration;
	
	@NotEmpty(message = "DoctorName cannot be empty")
	@Pattern(regexp = "[A-z]+",message = "DoctorName must contain only Alphabets")
	@Size(min = 1,max = 20)
	private String doctorName;

}
