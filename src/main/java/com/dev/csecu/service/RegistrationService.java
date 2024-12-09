package com.dev.csecu.service;

import com.dev.csecu.entity.Event;
import com.dev.csecu.entity.Registration;
import com.dev.csecu.entity.User;
import com.dev.csecu.repository.RegistrationRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;



    @Transactional
    public void saveRegistration(Registration registration) {

        registrationRepository.save(registration);


    }

    public List<Registration> registerList(long id) {
        List<Registration> registrations =  registrationRepository.findAllByEventId(id);// Retrieve all events from the database
        return registrations;
    }

}
