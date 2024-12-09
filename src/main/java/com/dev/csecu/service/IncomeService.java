package com.dev.csecu.service;

import com.dev.csecu.entity.EventIncomeDTO;

import com.dev.csecu.entity.Income;
import com.dev.csecu.entity.IncomeYearlySummary;
import com.dev.csecu.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeService {
    @Autowired
    IncomeRepository incomeRepository;

    public int getTotalIncome() {
        return incomeRepository.findAll().
                stream().
                map(Income::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add)
                .intValue();
    }

    public void saveIncome(Income income)
    {

        this.incomeRepository.save(income);
    }

    public List<Income> incomeList() {
        List<Income> incomes=incomeRepository.findAll();// Retrieve all events from the database
        return incomes;
    }



    public List<IncomeYearlySummary> getIncomeSummaryByYear() {

        return incomeRepository.getIncomeSummaryByYear();
    }


    public List<EventIncomeDTO> getEventIncomes() {
        return incomeRepository.findIncomesByevent();
    }


}
