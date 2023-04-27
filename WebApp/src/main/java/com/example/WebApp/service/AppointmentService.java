package com.example.WebApp.service;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO dto);
    List<AppointmentDTO> getAllAppointments();
    List<AppointmentDTO> getAllAppointmentsByDate(LocalDate date);
}
