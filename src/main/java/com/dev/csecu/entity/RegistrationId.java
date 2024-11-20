package com.dev.csecu.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class RegistrationId implements Serializable {

    @Column(name = "id")
    private Long id;

    @Column(name = "eventid")
    private Long eventId;
}
