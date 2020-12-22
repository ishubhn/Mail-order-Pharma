package com.mailorderpharma.drugservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.entity.SuccessResponse;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.exception.InvalidTokenException;
import com.mailorderpharma.drugservice.exception.StockNotFoundException;


/** Interface which have the methods of service class */
public interface DrugDetailsService {

	/**
	 * @param id
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 * @throws DrugNotFoundException
	 */
	DrugDetails getDrugById(String id, String token) throws InvalidTokenException, DrugNotFoundException;

	/**
	 * @param name
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 * @throws DrugNotFoundException
	 */
	DrugDetails getDrugByName(String name, String token) throws InvalidTokenException, DrugNotFoundException;

	/**
	 * @param id
	 * @param location
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 * @throws StockNotFoundException
	 * @throws DrugNotFoundException
	 */
	Stock getDispatchableDrugStock(String id, String location, String token)
			throws InvalidTokenException, StockNotFoundException, DrugNotFoundException;

	/**
	 * @param id
	 * @param location
	 * @param quantity
	 * @param token
	 * @return
	 * @throws InvalidTokenException
	 * @throws DrugNotFoundException
	 * @throws StockNotFoundException
	 */
	ResponseEntity<SuccessResponse> updateQuantity(String id, String location, int quantity, String token)
			throws InvalidTokenException,DrugNotFoundException,StockNotFoundException;

	/**
	 * @return
	 */
	List<DrugDetails> getAllDrugs();

}
