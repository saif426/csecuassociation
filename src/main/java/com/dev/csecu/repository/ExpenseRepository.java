package com.dev.csecu.repository;

import com.dev.csecu.entity.Expense;
import com.dev.csecu.entity.ExpenseYearlySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    Expense findExpenseById(long id);
    @Query("SELECT NEW com.dev.csecu.entity.ExpenseYearlySummary(YEAR(e.expenseDate), SUM(e.amount)) FROM Expense e GROUP BY YEAR(e.expenseDate)")
    List<ExpenseYearlySummary> getExpenseSummaryByYear();

}
