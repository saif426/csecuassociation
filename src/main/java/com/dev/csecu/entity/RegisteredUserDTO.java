package com.dev.csecu.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUserDTO {

    private Long studentId;
    private String name;
    private String mobile;
    private String email;

    // Constructor to match the query constructor
    public RegisteredUserDTO(Long studentId, String name, String mobile, String email) {
        this.studentId = studentId;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }
}
