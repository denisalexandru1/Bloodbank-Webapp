package com.example.WebApp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "donor")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    @Column(unique=true)
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String bloodType;
    public Donor() {
    }
}
