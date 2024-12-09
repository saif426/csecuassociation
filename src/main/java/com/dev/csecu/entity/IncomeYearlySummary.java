package com.dev.csecu.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class IncomeYearlySummary {
    private int year;
    private BigDecimal totalAmount;
    public IncomeYearlySummary(Integer year, BigDecimal totalAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
}

