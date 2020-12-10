package com.mailorderpharma.subscription.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailorderpharma.subscription.entity.PrescriptionDetails;

public interface PrescriptionRepository extends JpaRepository<PrescriptionDetails,Long> {

}
