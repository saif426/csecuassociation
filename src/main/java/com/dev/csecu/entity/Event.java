package com.dev.csecu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "registration_start", nullable = false)
    private Date registrationStart;

    @Column(name = "registration_end", nullable = false)
    private Date registrationEnd;

    @Column(name = "Fee", nullable = false)
    private String fee;
    @Column(name = "venue", nullable = false)
    private String venue;
    // Constructors, getters, and setters
}
