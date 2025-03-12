package com.example.atmProject.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    private String role = "ADMIN"; // Varsayılan rol

}

