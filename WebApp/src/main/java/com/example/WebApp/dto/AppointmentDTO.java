package com.example.WebApp.dto;

import com.example.WebApp.entity.Center;
import com.example.WebApp.entity.Donor;

import java.time.LocalDate;
import java.util.UUID;

public class AppointmentDTO {
    public UUID uuid;
    public Donor donor;
    public Center center;
    public LocalDate date;
    public Boolean validated;
}
