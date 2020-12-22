package com.mailorderpharma.drugservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharma.drugservice.entity.DrugDetails;

/**JPA Repository which interacts with database*/
@Repository
public interface DrugDetailsRepository extends JpaRepository<DrugDetails, String>{

	/**
	 *@param id
	 *@return
	 */
	Optional<DrugDetails> findById(String id);
	
	/**
	 * @param name
	 * @return
	 */
	Optional<DrugDetails> findBydrugName(String name);

}