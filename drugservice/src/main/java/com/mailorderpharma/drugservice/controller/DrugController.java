package com.mailorderpharma.drugservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.entity.SuccessResponse;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.exception.InvalidTokenException;
import com.mailorderpharma.drugservice.exception.StockNotFoundException;
import com.mailorderpharma.drugservice.service.DrugDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(produces = "application/json", value="Manages drugs")
public class DrugController {

	@Autowired
	DrugDetailsService drugDetailsService;

	@ApiOperation(value = "Get all drugs", response = List.class)
	@GetMapping("/getAllDrugs")
	public List<DrugDetails> getAllDrugs() {
		return drugDetailsService.getAllDrugs();
	}
	
	@ApiOperation(value = "Search drug by id", response = DrugDetails.class)
	@GetMapping("/searchDrugsById/{id}")
	public DrugDetails getDrugById(@RequestHeader("Authorization") String token, @PathVariable("id") String id)
			throws InvalidTokenException, DrugNotFoundException {
		return drugDetailsService.getDrugById(id, token);
	}

	@ApiOperation(value = "Search drug by name", response = DrugDetails.class)
	@GetMapping("/searchDrugsByName/{name}")
	public DrugDetails getDrugByName(@RequestHeader("Authorization") String token, @PathVariable("name") String name)
			throws InvalidTokenException,DrugNotFoundException {
		return drugDetailsService.getDrugByName(name, token);
	}

	@ApiOperation(value = "Search stock by id", response = Stock.class)
	@GetMapping("/getDispatchableDrugStock/{id}/{location}")
	public Stock getDispatchableDrugStock(@RequestHeader("Authorization") String token, @PathVariable("id") String id,
			@PathVariable("location") String location) throws InvalidTokenException,StockNotFoundException, DrugNotFoundException {
		return drugDetailsService.getDispatchableDrugStock(id, location, token);
	}

	@ApiOperation(value = "Update quantity by stock", response = ResponseEntity.class)
	@PutMapping("/updateDispatchableDrugStock/{id}/{location}/{quantity}")
	public ResponseEntity<SuccessResponse> updateQuantity(@RequestHeader("Authorization") String token, @PathVariable("id") String id,
			@PathVariable("location") String location, @PathVariable("quantity") int quantity)
			throws InvalidTokenException,DrugNotFoundException,StockNotFoundException {
		return drugDetailsService.updateQuantity(id, location, quantity, token); 
	}
}
