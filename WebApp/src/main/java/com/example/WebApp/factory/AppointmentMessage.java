package com.example.WebApp.factory;

import com.example.WebApp.dto.AppointmentDTO;

public interface AppointmentMessage {
    void send(AppointmentDTO appointmentDTO);
}
