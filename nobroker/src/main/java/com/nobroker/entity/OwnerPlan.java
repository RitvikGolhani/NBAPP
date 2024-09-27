package com.nobroker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwnerPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerPlanId;
    private long userId;
    private boolean subscriptionActive;
    private LocalDate subscriptionActiveDate;
    private LocalDate subscriptionExpirationDate;
    private int numberOfDays;
}
