package com.nobroker.schedular;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.repository.OwnerPlanRepository;
import com.nobroker.service.impl.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ExpirationSchedular {

    @Autowired
    private OwnerPlanService ownerPlanService;

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void checkExpiration(){
        ownerPlanService.checkAndUpdateExpiredSubscriptions();
    }
}

//@Component
//public class ExpirationSchedular {
//    @Autowired
//    private OwnerPlanRepository ownerPlanRepository;
//    @Scheduled(cron = "0 0 0 * * *")
//    public void checkExpiration(){
//         List<OwnerPlan> activePlans = ownerPlanRepository.findBySubscriptionActiveTrueAndSubscriptionDateBefore(LocalDate.now());
//         for(OwnerPlan plan:activePlans){
//             plan.setSubscriptionActive(false);
//             ownerPlanRepository.save(plan);
//         }
//    }
//
//}
