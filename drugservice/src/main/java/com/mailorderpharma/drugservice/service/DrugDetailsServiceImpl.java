package com.mailorderpharma.drugservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mailorderpharma.drugservice.dao.DrugDetailsRepository;
import com.mailorderpharma.drugservice.dao.DrugLocationRepository;
import com.mailorderpharma.drugservice.entity.DrugDetails;
import com.mailorderpharma.drugservice.entity.DrugLocationDetails;
import com.mailorderpharma.drugservice.entity.Stock;

@Service
public class DrugDetailsServiceImpl implements DrugDetailsService{
	
	@Autowired
	private DrugDetailsRepository drugRepo;
	
	@Autowired
	private DrugLocationRepository locationRepo;
	
	@Override
	public DrugDetails getDrugById(String id) {
		return drugRepo.findById(id).get();
	}

	@Override
	public DrugDetails getDrugByName(String name) {
		return drugRepo.findBydrugName(name).get();
	}

	@Override
	public Stock getDispatchableDrugStock(String id, String location) {
		//System.out.println(drugRepo.findDispatchableDrugStock(id, location));
		DrugDetails details = drugRepo.findById(id).get();
		for(DrugLocationDetails dld: details.getDruglocationQuantities()) {
			if(dld.getLocation().equals(location)) {
				Stock stock = new Stock(id, details.getDrugName(), details.getExpiryDate(), dld.getQuantity());
				return stock;
			}
		}
		return null;
	}

	@Override
	public ResponseEntity<?> updateQuantity(String id, String location, int quantity) {
		DrugDetails details = drugRepo.findById(id).get();
		for(DrugLocationDetails dld: details.getDruglocationQuantities()) {
			if(dld.getLocation().equals(location)) {
				int tempQuantity = dld.getQuantity();
				if(tempQuantity >= quantity) {
					tempQuantity -= quantity;
					String tempId = dld.getSerialId();
					DrugLocationDetails toUpdate = locationRepo.getOne(tempId);
					toUpdate.setQuantity(tempQuantity);
					locationRepo.save(toUpdate);
					return new ResponseEntity<>(HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
