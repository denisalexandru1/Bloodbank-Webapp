package com.example.WebApp.service.impl;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import com.example.WebApp.mapper.AppointmentMapper;
import com.example.WebApp.repository.AppointmentRepository;
import com.example.WebApp.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment createdAppointment = appointmentRepository.save(appointmentMapper.toAppointment(dto));
        return appointmentMapper.toDTO(createdAppointment);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDate(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAllByDate(date);
        return appointments.stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }
}
