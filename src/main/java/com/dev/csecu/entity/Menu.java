package com.dev.csecu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Submenu> submenus = new ArrayList<>();

    // Getters and Setters
    public void addSubmenu(Submenu submenu) {
        submenus.add(submenu);
        submenu.setMenu(this);
    }
}
