package com.mailorderpharma.subscription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
//errordecoder have less response time then global exceptionhandler
	 

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 404: {
			log.info("Error took place when using Feign client to send HTTP Request. Status code 404");
			return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Drug is not available");
		}
		default:
			return new Exception(response.reason());
		}
	}

}