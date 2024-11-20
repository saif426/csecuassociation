package com.dev.csecu.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "registration")
public class Registration  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentid", nullable = false)
    private Long studentId;

    @Column(name = "eventid", nullable = false)
    private Long eventId;

    @Column(name = "registrationdate")
    private Date registrationDate;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "student_name", nullable = false, length = 100)
    private String name;

    // Constructors, getters, and setters
}

