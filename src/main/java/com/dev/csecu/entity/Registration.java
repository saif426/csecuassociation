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
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "studentid", referencedColumnName = "StudentId", nullable = false)
    private User student;

    @Column(name = "fee", nullable = false)
    private Long fee;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;


}

