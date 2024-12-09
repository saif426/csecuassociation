package com.dev.csecu.controller;

import com.dev.csecu.entity.*;
import com.dev.csecu.repository.EventRepository;
import com.dev.csecu.repository.ExpenseRepository;
import com.dev.csecu.repository.RegistrationRepository;
import com.dev.csecu.repository.UserRepository;
import com.dev.csecu.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;


    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private EventService eventService;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private SubmenuService submenuService;
    @Autowired
    private IncomeService incomeService;


    @GetMapping("/expenseForm")
    public String expenseCreate(HttpSession session, Model model)
    {

        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);

        return "expenseForm";
    }


    @PostMapping("/expenseSave")
    public String expenseSave(@ModelAttribute("expense") Expense expense)
    {
        expenseService.saveExpense(expense);
        return "redirect:/expenseShow";
    }
    @GetMapping("/expenseShow")
    public String showExpenses(HttpSession session, Model model) {


        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);



        List<ExpenseYearlySummary> expenseSummaryByYear = expenseService.getExpenseSummaryByYear();

        model.addAttribute("expenseSummaryByYear", expenseSummaryByYear);

        List<EventExpenseDTO> eventExpenses = expenseService.getEventExpenses();
        model.addAttribute("eventExpenses", eventExpenses);


        List<IncomeYearlySummary> incomeSummaryByYear=incomeService.getIncomeSummaryByYear();
        model.addAttribute("incomeSummaryByYear", incomeSummaryByYear);
        List<EventIncomeDTO> eventIncome=incomeService.getEventIncomes();
        model.addAttribute("eventIncome", eventIncome);




        return "expenseCert";



    }
}
