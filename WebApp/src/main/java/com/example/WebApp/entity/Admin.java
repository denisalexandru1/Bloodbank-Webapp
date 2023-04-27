package com.example.WebApp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    @Column(unique=true)
    public String email;
    public String password;
}
