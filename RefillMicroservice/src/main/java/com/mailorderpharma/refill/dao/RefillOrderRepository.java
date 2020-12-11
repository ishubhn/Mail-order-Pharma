package com.mailorderpharma.refill.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mailorderpharma.refill.entity.RefillOrder;

@Repository
public interface RefillOrderRepository extends JpaRepository<RefillOrder,Integer> {
	
//	
//	@Query("select s from RefillOrder s")
//	public List<RefillOrder> getAll();

}
