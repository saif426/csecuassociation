package com.dev.csecu.service;

import com.dev.csecu.entity.EventExpenseDTO;
import com.dev.csecu.entity.Expense;
import com.dev.csecu.entity.ExpenseYearlySummary;
import com.dev.csecu.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    public int getTotalExpense() {
        return expenseRepository.findAll()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .intValue();
    }

    public void saveExpense(Expense expense)
    {

        this.expenseRepository.save(expense);
    }

    public List<Expense> expenseList() {
        List<Expense> Expenses = expenseRepository.findAll(); // Retrieve all events from the database
        return Expenses;
    }



    public List<ExpenseYearlySummary> getExpenseSummaryByYear() {
        return expenseRepository.getExpenseSummaryByYear();
    }


    public List<EventExpenseDTO> getEventExpenses() {
        return expenseRepository.findExpensesByevent();
    }


}
