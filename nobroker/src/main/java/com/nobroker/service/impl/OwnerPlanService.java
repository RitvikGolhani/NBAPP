package com.nobroker.service.impl;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.repository.OwnerPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class OwnerPlanService {

    @Autowired
    private OwnerPlanRepository ownerPlanRepository;


    public void checkAndUpdateExpiredSubscriptions() {
        LocalDate today = LocalDate.now();
        List<OwnerPlan> expiredSubscriptions = ownerPlanRepository.findBySubscriptionExpirationDateBeforeAndSubscriptionActiveTrue(today);

        // Update status of expired subscriptions
        for (OwnerPlan subscription : expiredSubscriptions) {
            subscription.setSubscriptionActive(false);
            ownerPlanRepository.save(subscription);
        }
    }
    public OwnerPlan createSubscription(long userId, int duration) {
        OwnerPlan ownerPlan = new OwnerPlan();
        ownerPlan.setUserId(userId);
        ownerPlan.setSubscriptionActive(true);
        ownerPlan.setSubscriptionActiveDate(LocalDate.now());

        // Set expiration date based on duration
        LocalDate expirationDate = LocalDate.now().plusDays(duration);
        ownerPlan.setSubscriptionExpirationDate(expirationDate);

        ownerPlan.setNumberOfDays(duration);

        ownerPlanRepository.save(ownerPlan);
        return ownerPlan;
    }

}






