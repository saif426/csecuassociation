package com.dev.csecu.service;

import com.dev.csecu.entity.Event;
import com.dev.csecu.entity.Student;
import com.dev.csecu.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public void saveEvent(Event event)
    {
        this.eventRepository.save(event);
    }

    public List<Event> eventList() {
        List<Event> events = eventRepository.findAll(); // Retrieve all events from the database
        return events;
    }

    public Event findById(long eventId) {
        return eventRepository.findEventById(eventId);
    }
}
