package com.dev.csecu.repository;

import com.dev.csecu.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findEventById(long id);
}
