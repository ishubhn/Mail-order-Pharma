package com.mailorderpharma.drugservice.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	private String drugId;
	private String drugName;
	private Date expiryDate;
	private int stock;
	
}
