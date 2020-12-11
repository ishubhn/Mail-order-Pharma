package com.mailorderpharma.refill.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mailorderpharma.refill.entity.RefillOrderSubscription;

@Repository
@Transactional
public interface RefillOrderSubscriptionRepository extends JpaRepository<RefillOrderSubscription, Long> {
	
	@Modifying
	@Query("delete from RefillOrderSubscription where subscriptionId=?1")
	public int deleteBySubscriptionId(long subscriptionId);

}
