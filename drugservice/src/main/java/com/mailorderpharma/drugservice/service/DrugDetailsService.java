package com.mailorderpharma.drugservice.service;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;

public interface DrugDetailsService {

	DrugDetails getDrugById(String id);

	DrugDetails getDrugByName(String name);

	Stock getDispatchableDrugStock(String id, String location);

	ResponseEntity<?> updateQuantity(String id, String location, int quantity);

}
