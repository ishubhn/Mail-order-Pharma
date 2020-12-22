package com.mailorderpharma.drugservice.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Model class for the business details*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	/**
	 * Id for the drug
	 */
	private String drugId;
	/**
	 * Name of the drug
	 */
	private String drugName;
	/**
	 * Expiry date for the drug
	 */
	private Date expiryDate;
	/**
	 * Quantity of the drug left
	 */
	private int stock;
	
}
