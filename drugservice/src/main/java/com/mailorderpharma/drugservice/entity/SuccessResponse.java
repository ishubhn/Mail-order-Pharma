package com.mailorderpharma.drugservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Model class for the business details*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
	/**
	 * Response Message for the given request
	 */
	private String responseMessage;
}
