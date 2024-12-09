package com.dev.csecu.controller;

import com.dev.csecu.entity.*;
import com.dev.csecu.repository.EventRepository;
import com.dev.csecu.repository.ExpenseRepository;
import com.dev.csecu.repository.RegistrationRepository;
import com.dev.csecu.repository.UserRepository;
import com.dev.csecu.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
    @Autowired
    private SubmenuService submenuService;
    @Autowired
    private IncomeService incomeService;


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





    @GetMapping("/memberSignForm")
    public String memberSignForm()
    {

        return "memberForm";
    }
    @PostMapping("/userSignup")
    public String userSignup(@ModelAttribute("User") User user,Model model,RedirectAttributes redirectAttributes)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPassword());  // Hash the password

        // Set the hashed password to the user object
        user.setPassword(hashedPassword);


        userService.saveUser(user);
        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", "Registration Successfully Done!");

        return "redirect:/login"; // Redirect to show save message
    }

    @GetMapping("/showSaveMessage")
    public String showSaveMessage() {
        return "saveMessage"; // This will be a separate HTML template to show the save message
    }





    @PostMapping("/performlogin")
    public String processLogin(@RequestParam("userId") String userId,
                               @RequestParam("password") String password,
                               Model model,
                               HttpSession session) {

        int u_Id = Integer.parseInt(userId);
        String pass = password;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User u1 = userRepository.findUserByStudentId(u_Id);
        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);

        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);



        if (u1 != null && encoder.matches(pass, u1.getPassword())) {
            // Store user details in the session
            session.setAttribute("loggedInUser", u1);

            // Additional attributes for admin
            if (u1.getRole() == 1) {
                long totalUsers = userRepository.count();
                long totalEvents = eventRepository.count();
                int totalExpense = expenseService.getTotalExpense();
                int totalIncome=incomeService.getTotalIncome();

                model.addAttribute("totalUsers", totalUsers);
                model.addAttribute("totalEvents", totalEvents);
                model.addAttribute("totalExpense", totalExpense);
                model.addAttribute("totalIncome", totalIncome);
                model.addAttribute("User", u1);

                return "admin"; // Admin menu
            } else if (u1.getRole() == 0) {
                model.addAttribute("User", u1);
                return "user"; // User menu
            }
        } else {
            // Handle authentication failure
            model.addAttribute("error", "Incorrect UserId or Password");
            return "login"; // Redirect to login page
        }

        return "login"; // In case of unexpected behavior
    }



    @GetMapping("/userShow")
    public String userShow(HttpSession session, Model model) {


        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);

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





    @PostMapping("/registrationInfo")
    public String bookEvent(@RequestParam("eventId") Long eventId,Model model) {


        List<Registration> registerUser = registrationService.registerList(eventId);
        model.addAttribute("registerUser", registerUser);
        return "registerUserList";
         // Redirect to a success page
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

    @GetMapping("/showContact")
    public String showcontact()
    {
        return "contact";
    }


    @GetMapping("/logout")
    public String logoutUser(HttpSession session)
    {
        session.invalidate();
        return "login";
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
    public String showBatchSelectionPage(HttpSession session, Model model) {


        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);


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







// expense info



}




