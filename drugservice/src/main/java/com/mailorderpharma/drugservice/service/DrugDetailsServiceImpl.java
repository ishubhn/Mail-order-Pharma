package com.mailorderpharma.drugservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.drugservice.dao.DrugDetailsRepository;
import com.mailorderpharma.drugservice.dao.DrugLocationRepository;
import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.DrugLocationDetails;
import com.mailorderpharma.drugservice.entity.Stock;
import com.mailorderpharma.drugservice.exception.DrugNotFoundException;
import com.mailorderpharma.drugservice.exception.InvalidTokenException;
import com.mailorderpharma.drugservice.exception.StockNotFoundException;
import com.mailorderpharma.drugservice.restclients.AuthFeign;

@Service
public class DrugDetailsServiceImpl implements DrugDetailsService {

	@Autowired
	private DrugDetailsRepository drugRepo;

	@Autowired
	private DrugLocationRepository locationRepo;

	@Autowired
	private AuthFeign authFeign;

	@Override
	public DrugDetails getDrugById(String id, String token) throws InvalidTokenException, DrugNotFoundException {
		DrugDetails drugDetails = null;
		if (authFeign.getValidity(token).getBody().isValid()) {
				drugDetails = drugRepo.findById(id).orElseThrow(()-> new DrugNotFoundException("Drug Not Found"));
		} else
			throw new InvalidTokenException("Invalid Credentials");
		return drugDetails;
	}

	@Override
	public DrugDetails getDrugByName(String name, String token) throws InvalidTokenException, DrugNotFoundException {
		if (authFeign.getValidity(token).getBody().isValid()) {
			try {
				return drugRepo.findBydrugName(name).get();
			} catch (Exception e) {
				throw new DrugNotFoundException("Drug Not Found");
			}
		} else
			throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public Stock getDispatchableDrugStock(String id, String location, String token)
			throws InvalidTokenException, StockNotFoundException, DrugNotFoundException {
		if (authFeign.getValidity(token).getBody().isValid()) {
			DrugDetails details = null;
			try {
				details = drugRepo.findById(id).get();
			} catch (Exception e) {

				throw new DrugNotFoundException("Drug Not Found");
			}
			Stock stock = null;
			for (DrugLocationDetails dld : details.getDruglocationQuantities()) {
				if (dld.getLocation().equals(location)) {
					stock = new Stock(id, details.getDrugName(), details.getExpiryDate(), dld.getQuantity());
				}
			}
			if (stock == null)
				throw new StockNotFoundException("Stock Unavailable at your location");
			else
				return stock;
		}
		throw new InvalidTokenException("Invalid Credentials");
	}

	@Override
	public ResponseEntity<?> updateQuantity(String id, String location, int quantity, String token)
			throws InvalidTokenException, DrugNotFoundException, StockNotFoundException {
		if (authFeign.getValidity(token).getBody().isValid()) {
			DrugDetails details = null;
			try {
				details = drugRepo.findById(id).get();
			} catch (Exception e) {

				throw new DrugNotFoundException("Drug Not Found");
			}
			List<DrugLocationDetails> dummy = details.getDruglocationQuantities().stream()
					.filter(x -> x.getLocation().equalsIgnoreCase(location)).collect(Collectors.toList());

			if (dummy.size() == 0)
				throw new StockNotFoundException("Stock Unavailable at your location");

			if (dummy.get(0).getQuantity() >= quantity) {

				Optional<DrugLocationDetails> tempDetails = locationRepo.findById(dummy.get(0).getSerialId());
				int val = tempDetails.get().getQuantity() - quantity;
				tempDetails.get().setQuantity(val);
				locationRepo.save(tempDetails.get());
				return new ResponseEntity<>(new String("Refill Done Successfully"), HttpStatus.OK);
			} else
				throw new StockNotFoundException("Stock Unavailable at your location");
		}
		throw new InvalidTokenException("Invalid Credentials");
	}
}
