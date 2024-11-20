package com.dev.csecu.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ExpenseYearlySummary {
    private int year;
    private BigDecimal totalAmount;
    public ExpenseYearlySummary(Integer year, BigDecimal totalAmount) {
        this.year = year;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
}

