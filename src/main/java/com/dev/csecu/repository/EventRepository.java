package com.dev.csecu.repository;

import com.dev.csecu.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findEventById(long id);

    @Query("SELECT e FROM Event e WHERE e.eventDate > CURRENT_TIMESTAMP")
    List<Event> findUpcomingEvents();

    @Query("SELECT e FROM Event e WHERE e.eventDate < CURRENT_TIMESTAMP")
    List<Event> findCompletedEvents();

}
