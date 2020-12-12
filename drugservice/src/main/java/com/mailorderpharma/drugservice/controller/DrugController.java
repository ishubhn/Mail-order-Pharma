package com.mailorderpharma.drugservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.exception.InvalidTokenException;
import com.mailorderpharma.drugservice.exception.StockNotFoundException;
import com.mailorderpharma.drugservice.service.DrugDetailsService;

@RestController
public class DrugController {

	@Autowired
	DrugDetailsService drugDetailsService;

	@GetMapping("/searchDrugsById/{id}")
	public DrugDetails getDrugById(@RequestHeader("Authorization") String token, @PathVariable("id") String id)
			throws InvalidTokenException, DrugNotFoundException {
		return drugDetailsService.getDrugById(id, token);
	}

	@GetMapping("/searchDrugsByName/{name}")
	public DrugDetails getDrugByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name)
			throws InvalidTokenException,DrugNotFoundException {
		return drugDetailsService.getDrugByName(name, token);
	}

	@GetMapping("/getDispatchableDrugStock/{id}/{location}")
	public Stock getDispatchableDrugStock(@RequestHeader("Authorization") String token, @PathVariable("id") String id,
			@PathVariable("location") String location) throws InvalidTokenException,StockNotFoundException, DrugNotFoundException {
		return drugDetailsService.getDispatchableDrugStock(id, location, token);
	}

	@PutMapping("/updateDispatchableDrugStock/{id}/{location}/{quantity}")
	public ResponseEntity<?> updateQuantity(@RequestHeader("Authorization") String token, @PathVariable("id") String id,
			@PathVariable("location") String location, @PathVariable("quantity") int quantity)
			throws InvalidTokenException,DrugNotFoundException,StockNotFoundException {
		return drugDetailsService.updateQuantity(id, location, quantity, token);
	}
}
