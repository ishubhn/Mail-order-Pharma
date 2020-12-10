package com.mailorderpharma.subscription.entity;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DrugLocationDetails {

	@Id 
	private String serialId;
	private String location;
	private int quantity;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "drugId")
	private DrugDetails drugDetails;
}

