package com.example.WebApp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    @Column(unique=true)
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String cnp;
    @OneToOne
    public Center center;
    public Doctor() {
    }
}
