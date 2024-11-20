package com.dev.csecu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "User")
public class User {

    @Id
    @Column(name = "StudentId", nullable = false, unique = true)
    private int studentId; // Manually provided unique Student ID

    @Column(name = "Name", nullable = false)
    private String name; // Student's name

    @Column(name = "Batch", nullable = false)
    private int batch; // Batch as an integer

    @Column(name = "Session", nullable = false)
    private String session; // Academic session (e.g., 2020-2024)

    @Column(name = "Email", nullable = false, unique = true)
    private String email; // Unique email address

    @Column(name = "Password", nullable = false)
    private String password; // Password for the user

    @Column(name = "Role", nullable = false)
    private int role = 0; // Role with default value 0

    @Column(name = "Pic")
    private String pic; // Path or URL for profile picture

    @Column(name = "Mobile", nullable = false, unique = true)
    private String mobile; // Unique mobile number

    @Column(name = "is_Representative", nullable = false)
    private int is_Representative = 0;

}
