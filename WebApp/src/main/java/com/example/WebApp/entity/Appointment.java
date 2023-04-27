package com.example.WebApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    @ManyToOne
    public Donor donor;
    @ManyToOne
    public Center center;
    public LocalDate date;
    public Boolean validated;
}
