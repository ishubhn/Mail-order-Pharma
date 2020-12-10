package com.mailorderpharma.drugservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.service.DrugDetailsService;

@RestController
public class DrugController {

	@Autowired 
	DrugDetailsService drugDetailsService;
	
	@GetMapping("/searchDrugsById/{id}")
	public DrugDetails getDrugById(@PathVariable("id") String id) {
		return drugDetailsService.getDrugById(id);
	}
	
	@GetMapping("/searchDrugsByName/{name}")
	public DrugDetails getDrugByName(@PathVariable("name") String name) {
		return drugDetailsService.getDrugByName(name);
	}
	
	@GetMapping("/getDispatchableDrugStock/{id}/{location}")
	public Stock getDispatchableDrugStock(@PathVariable("id") String id, @PathVariable("location") String location) {
		return drugDetailsService.getDispatchableDrugStock(id, location);
	}
	
	@PutMapping("/updateDispatchableDrugStock/{id}/{location}/{quantity}")
	public ResponseEntity<?> updateQuantity(@PathVariable("id") String id, @PathVariable("location") String location, @PathVariable("quantity") int quantity) {
		return drugDetailsService.updateQuantity(id, location, quantity);
	}
}
