package com.nobroker.controller;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.service.impl.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerPlanController {

    @Autowired
    private OwnerPlanService ownerPlanService;

    // http://localhost:8080/subscriptions
    @PostMapping("/subscriptions/{userId}/{duration}")
    public OwnerPlan createSubscription(@PathVariable long userId,@PathVariable int duration) {

           return ownerPlanService.createSubscription(userId,duration);

    }
}
