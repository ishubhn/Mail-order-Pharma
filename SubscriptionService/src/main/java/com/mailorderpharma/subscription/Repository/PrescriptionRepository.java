package com.mailorderpharma.subscription.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;

public interface PrescriptionRepository extends JpaRepository<PrescriptionDetails,Long> {

	 @Query(value = "SELECT drugName FROM PrescriptionDetails u WHERE MEMBER_ID = ?1")
	 String findDrugByPrescriptionId(Long pId);
}
