package com.mailorderpharma.webportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 404: {
			log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status()
					+ ", methodKey = " + methodKey);

			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Content is not available");
		}
		case 503: {
			log.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status()
					+ ", methodKey = " + methodKey);

			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Temporarily Service is unavailable, Please try again later");
		}
		default:
			return new Exception(response.reason());
		}
	}

}