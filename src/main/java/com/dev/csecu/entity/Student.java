package com.dev.csecu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "dept")
    private String department;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    // Constructors, getters, and setters
}

