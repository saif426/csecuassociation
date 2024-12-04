package com.dev.csecu.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Submenu")
public class Submenu {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id; // Unique identifier for the submenu

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "submenu_ibfk_1"))
    private Menu menu; // Reference to the parent menu (many-to-one relationship)

    @Column(name = "name", nullable = false)
    private String name; // Name of the submenu item

    @Column(name = "url", nullable = false)
    private String url; // URL for the submenu item

    @Column(name = "role", nullable = true, columnDefinition = "int default 0")
    private int role = 0; // Role with a default value of 0
}
