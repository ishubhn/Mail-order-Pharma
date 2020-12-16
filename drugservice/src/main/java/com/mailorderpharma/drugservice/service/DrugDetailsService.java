package com.mailorderpharma.drugservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.entity.SuccessResponse;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.exception.InvalidTokenException;
import com.mailorderpharma.drugservice.exception.StockNotFoundException;

public interface DrugDetailsService {

	DrugDetails getDrugById(String id, String token) throws InvalidTokenException, DrugNotFoundException;

	DrugDetails getDrugByName(String name, String token) throws InvalidTokenException, DrugNotFoundException;

	Stock getDispatchableDrugStock(String id, String location, String token)
			throws InvalidTokenException, StockNotFoundException, DrugNotFoundException;

	ResponseEntity<SuccessResponse> updateQuantity(String id, String location, int quantity, String token)
			throws InvalidTokenException,DrugNotFoundException,StockNotFoundException;

	List<DrugDetails> getAllDrugs();

}
