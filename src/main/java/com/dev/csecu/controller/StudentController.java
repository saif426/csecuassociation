package com.dev.csecu.controller;

import com.dev.csecu.entity.*;
import com.dev.csecu.repository.EventRepository;
import com.dev.csecu.repository.ExpenseRepository;
import com.dev.csecu.repository.RegistrationRepository;
import com.dev.csecu.repository.UserRepository;
import com.dev.csecu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class StudentController {

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


    @GetMapping("/login")
    public String showLoginForm() {
        // This method will be called when accessing the login page
        return "login"; // Assuming you have a login.html or login.jsp Thymeleaf template
    }


    @GetMapping("/showLoginForm")
    public String showNewLoginForm()
    {
         return "login";
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Handle both "datetime-local" and MySQL-compatible formats
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @GetMapping("/eventForm")
    public String eventCreate()
    {
        return "eventForm";
    }

    @PostMapping("/eventSave")
    public String eventSave(@ModelAttribute("event") Event event)
    {
        eventService.saveEvent(event);
        return "redirect:/upEventShow";
    }
    @GetMapping("/eventShow")
    public String showEvents(Model model) {
        List<Event> events = eventService.eventList();
        model.addAttribute("events", events);
        return "eventList"; // Return the name of the Thymeleaf template to render
    }
    @GetMapping("/upEventShow")
    public String showUpEvents(Model model) {
        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);
        return "eventList"; // Return the name of the Thymeleaf template to render
    }
    @GetMapping("/eventAll")
    public String showAllEvents(Model model) {
        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);

        List<Event> eventAll = eventRepository.findCompletedEvents();
        model.addAttribute("eventAll", eventAll);

        return "eventListAll"; // Return the name of the Thymeleaf template to render
    }




    @GetMapping("/memberSignForm")
    public String memberSignForm()
    {

        return "memberForm";
    }
    @PostMapping("/userSignup")
    public String userSignup(@ModelAttribute("User") User user,Model model)
    {
        userService.saveUser(user);
        model.addAttribute("saveMessage", "User saved successfully!"); // Add a success message
        return "login"; // Redirect to show save message
    }

    @GetMapping("/showSaveMessage")
    public String showSaveMessage() {
        return "saveMessage"; // This will be a separate HTML template to show the save message
    }


    @PostMapping("/login")
    public String processLogin(@RequestParam("userId") String userId,
                               @RequestParam("password") String password, Model model) {

        int u_Id=Integer.parseInt(userId);
        String pass=password;

        User u1= userRepository.findUserByStudentId(u_Id);

        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);
            if(u1!=null && pass.equals(u1.getPassword()) && u1.getRole()==1)
            {
                long totalUsers = userRepository.count();
                long totalEvents = eventRepository.count();
                int totalExpense=expenseService.getTotalExpense();
                model.addAttribute("totalUsers", totalUsers);
                model.addAttribute("totalEvents", totalEvents);
                model.addAttribute("totalExpense", totalExpense);
                model.addAttribute("User", u1);
                return "admin";
            }
            else if(u1!=null && pass.equals(u1.getPassword()) && u1.getRole()==0)
            {
                model.addAttribute("User", u1);
                return "user";
            }
            else {
                // Handle authentication failure, maybe return an error page
                model.addAttribute("error", "Incorrect UserId or Password");
                return "login";
            }
    }
    @GetMapping("/userShow")
    public String userShow(Model model)
    {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "userList"; // Return the name of the Thymeleaf template to render

    }

@PostMapping("/registration")
public String registrationSave(@RequestParam("eventId") Long eventId,Model model)
{
    Event event = eventService.findById(eventId);
    System.out.println(eventId);
    model.addAttribute("event", event);
    return "registrationForm";
}

    @PostMapping("/register")
    public String registration(@ModelAttribute("register") Registration registration)
    {
       registrationService.saveRegistration(registration);
        return "user";
    }


    @GetMapping("/expenseForm")
    public String expenseCreate()
    {
        return "expenseForm";
    }
    @PostMapping("/expenseSave")
    public String expenseSave(@ModelAttribute("expense") Expense expense)
    {
        expenseService.saveExpense(expense);
        return "redirect:/expenseShow";
    }
    @GetMapping("/expenseShow")
    public String showExpenses(Model model) {
        //List<Expense> expenses = expenseService.expenseList();
        //model.addAttribute("expenses", expenses);
       // return "expenseList"; // Return the name of the Thymeleaf template to render

        List<ExpenseYearlySummary> expenseSummaryByYear = expenseService.getExpenseSummaryByYear();

        model.addAttribute("expenseSummaryByYear", expenseSummaryByYear);

        List<EventExpenseDTO> eventExpenses = expenseService.getEventExpenses();
        model.addAttribute("eventExpenses", eventExpenses);
        return "expenseCert";



    }



    @PostMapping("/registrationInfo")
    public String bookEvent(@RequestParam("eventId") Long eventId,Model model) {


        List<Registration> registerUser = registrationService.registerList(eventId);
        model.addAttribute("registerUser", registerUser);
        return "registerUserList";
         // Redirect to a success page
    }
    @GetMapping("/eventShowAdmin")
    public String showEventAdmin(Model model) {
        List<Event> events = eventService.eventList();
        model.addAttribute("events", events);
        return "eventListAdmin"; // Return the name of the Thymeleaf template to render
    }


    @GetMapping("/showHistory")
    public String showHistory()
    {
        return "history";
    }


    @GetMapping("/showVision")
    public String showVisionAndMission()
    {
        return "vision";
    }


    @GetMapping("/showBatch")
    public String showBatchRepresentative(Model model)
    {
        List<User> users = userService.getCRGroupedByBatch();

        Map<Integer, List<User>> groupedUsers = users.stream()
                .collect(Collectors.groupingBy(User::getBatch));

        groupedUsers.forEach((batch, userList) -> {
            System.out.println("Batch " + batch + ": " + userList.size() + " students");
            userList.forEach(user -> System.out.println("  Student: " + user.getName()));
        });

        model.addAttribute("groupedUsers", groupedUsers);
        return "batchRepresentative";


    }

    @GetMapping("/usershowPage")
    public String showBatchSelectionPage(Model model) {
        List<User> users = userService.getAllUsersGroupedByBatch();

        Map<Integer, List<User>> groupedUsers = users.stream()
                .collect(Collectors.groupingBy(User::getBatch));

        groupedUsers.forEach((batch, userList) -> {
            System.out.println("Batch " + batch + ": " + userList.size() + " students");
            userList.forEach(user -> System.out.println("  Student: " + user.getName()));
        });

        model.addAttribute("groupedUsers", groupedUsers);
        return "userShow";

    }

    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers(@RequestParam String batch) {
        if ("None".equals(batch)) {
            return userRepository.findAll();
        }
        return userRepository.findByBatch(Integer.parseInt(batch));
    }


}




