package com.example.WebApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID uuid;
    public String name;
    public String address;
    public Integer startHour;
    public Integer endHour;
    public Integer maxDonors;
    public Integer currentDonors;
    public Center() {
    }
}
