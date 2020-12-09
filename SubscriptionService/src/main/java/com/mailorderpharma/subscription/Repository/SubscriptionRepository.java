package com.mailorderpharma.subscription.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailorderpharma.subscription.entity.SubscriptionDetails;

public interface SubscriptionRepository extends JpaRepository<SubscriptionDetails, Long> {

}
