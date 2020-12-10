package com.mailorderpharma.drugservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharma.drugservice.entity.DrugDetails;

@Repository
public interface DrugDetailsRepository extends JpaRepository<DrugDetails, String>{

	Optional<DrugDetails> findById(String id);
	
	Optional<DrugDetails> findBydrugName(String name);
	
//	@Query(value = "Select drug_details.drug_id, drug_name, quantity from drug_details inner join drug_Location_details On drug_details.drug_id = drug_location_details.drug_id and drug_details.drug_id = :drug_id and location = :location", nativeQuery=true)
//	Stock findDispatchableDrugStock(@Param("drug_id")String id, @Param("location")String location);

//	   public interface Stock {
//
//	     String getDrug_Id();
//	     String getDrug_Name();
//	     int getquantity();
//
//	  }
}