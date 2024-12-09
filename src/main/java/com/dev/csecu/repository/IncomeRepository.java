package com.dev.csecu.repository;


import com.dev.csecu.entity.EventExpenseDTO;
import com.dev.csecu.entity.EventIncomeDTO;
import com.dev.csecu.entity.Income;
import com.dev.csecu.entity.IncomeYearlySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income,Long> {
    Income findIncomeById(Long id);
    @Query("SELECT NEW com.dev.csecu.entity.IncomeYearlySummary(YEAR(e.incomeDate), SUM(e.amount)) " +
            "FROM Income e GROUP BY YEAR(e.incomeDate)")
    List<IncomeYearlySummary> getIncomeSummaryByYear();

    @Query("SELECT e.eventName AS eventName, SUM(e.amount) AS totalAmount FROM" +
            " Income e GROUP BY e.eventName")
    List<EventIncomeDTO> findIncomesByevent();

}
