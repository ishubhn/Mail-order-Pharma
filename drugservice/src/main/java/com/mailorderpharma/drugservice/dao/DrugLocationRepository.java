package com.mailorderpharma.drugservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharma.drugservice.entity.DrugLocationDetails;

@Repository
public interface DrugLocationRepository extends JpaRepository<DrugLocationDetails, String>{

}
