package com.mailorderpharma.webportal.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefillDueResponse {

	String drugName;
	long id;
	long subscriptionId;
	String memberId;
	int refillQuantity;
	int refillTime;
}
