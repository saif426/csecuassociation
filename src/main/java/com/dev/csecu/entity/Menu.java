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
@Table(name = "Menu")
public class Menu {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id; // Unique identifier for the menu

    @Column(name = "name", nullable = false)
    private String name; // Name of the menu item
}
