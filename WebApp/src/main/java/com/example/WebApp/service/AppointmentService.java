package com.example.WebApp.service;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO dto);
    List<AppointmentDTO> getAllAppointments();
    Page<AppointmentDTO> getAllAppointmentsByPaging(UUID centerId, int page, int size);
    AppointmentDTO getAppointmentById(UUID uuid);
    List<AppointmentDTO> getAllAppointmentsByDate(LocalDate date);
    List<AppointmentDTO> getAllAppointmentsByDateAndCenterUUID(LocalDate date, UUID centerId);
    List<AppointmentDTO> getAllAppointmentsByDonorUUID(UUID donorId);
    AppointmentDTO updateAppointment(UUID uuid, AppointmentDTO dto);
}
