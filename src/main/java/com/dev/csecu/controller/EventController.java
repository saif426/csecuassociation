package com.dev.csecu.controller;

import com.dev.csecu.entity.*;
import com.dev.csecu.repository.*;
import com.dev.csecu.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EventController {

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
    private IncomeRepository incomeRepository;


    @GetMapping("/eventForm")
    public String eventCreate(HttpSession session, Model model)
    {

        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);


        return "eventForm";
    }

    @PostMapping("/eventSave")
    public String eventSave(@ModelAttribute("event") Event event)
    {
        eventService.saveEvent(event);
        return "redirect:/upEventShow";
    }
    @GetMapping("/eventShow")
    public String showEvents(HttpSession session, Model model)
    {

        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);


        List<Event> events = eventService.eventList();
        model.addAttribute("events", events);
        return "eventList"; // Return the name of the Thymeleaf template to render
    }
    @GetMapping("/upEventShow")
    public String showUpEvents(HttpSession session, Model model)
    {

        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);


        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);

        return "eventList"; // Return the name of the Thymeleaf template to render
    }
    @GetMapping("/eventAll")
    public String showAllEvents(HttpSession session, Model model)
    {

        User u1=(User)session.getAttribute("loggedInUser");
        model.addAttribute("User", u1);
        int userRole=u1.getRole();
        List<Menu> menus = submenuService.getMenusForUser(userRole);
        model.addAttribute("menus", menus);


        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);

        List<Event> eventAll = eventRepository.findCompletedEvents();
        model.addAttribute("eventAll", eventAll);


        if(userRole==1)
        {
            return "evenListAllAdmin";
        }

        return "eventListAll"; // Return the name of the Thymeleaf template to render
    }





    @GetMapping("/upEventShowHome")
    public String showAllEventsHome(HttpSession session, Model model)
    {


        List<Event> events = eventRepository.findUpcomingEvents();
        model.addAttribute("events", events);

        List<Event> eventAll = eventRepository.findCompletedEvents();
        model.addAttribute("eventAll", eventAll);

        return "eventListHome"; // Return the name of the Thymeleaf template to render
    }


    @PostMapping("/registerEvent")
    public String registerEvent(@RequestParam("event-id") Long eventId,
                                @RequestParam("studentId") Integer studentId,
                                @RequestParam("event-fee") Long fee,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("mobile") String mobile,
                                HttpSession session, RedirectAttributes redirectAttributes) {

        // Retrieve the event and student from the database using the IDs
        Event event = eventRepository.findEventById(eventId);
        User user = userRepository.findUserByStudentId(studentId);

        // Create a new Registration object
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setStudent(user);
        registration.setFee(fee);
        registration.setRegistrationDate(new Date());

        // Save the registration to the database
        registrationRepository.save(registration);
        Income income =new Income();
        income.setAmount(BigDecimal.valueOf(fee));
        income.setEventName(event.getName());
        income.setIncomeDate(new Date());
        incomeRepository.save(income);

        // Add success message
        redirectAttributes.addFlashAttribute("successMessage", "Registration Successfully Done!");

        // Redirect to the event list page
        return "redirect:/eventAll";
    }






}
