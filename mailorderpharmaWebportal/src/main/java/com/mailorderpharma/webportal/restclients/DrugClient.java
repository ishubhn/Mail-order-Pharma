package com.mailorderpharma.webportal.restclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.mailorderpharma.webportal.entity.DrugDetails;

@FeignClient(name = "drug-service", url = "localhost:8081/drugdetailapi")
public interface DrugClient {

	@GetMapping("/getAllDrugs")
	public List<DrugDetails> getAllDrugs(); 
}
