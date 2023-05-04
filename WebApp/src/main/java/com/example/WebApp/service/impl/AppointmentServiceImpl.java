package com.example.WebApp.service.impl;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import com.example.WebApp.mapper.AppointmentMapper;
import com.example.WebApp.repository.AppointmentRepository;
import com.example.WebApp.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Page<AppointmentDTO> getAllAppointmentsByPaging(UUID centerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Appointment> appointmentsPage = appointmentRepository.findAllByCenterUuid(centerId, pageable);
        List<AppointmentDTO> appointmentDTOs = appointmentsPage.getContent().stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
        return new PageImpl<>(appointmentDTOs, pageable, appointmentsPage.getTotalElements());
    }

    @Override
    public AppointmentDTO getAppointmentById(UUID uuid) {
        Optional<Appointment> appointment = appointmentRepository.findById(uuid);
        if(appointment.isPresent()){
            return appointmentMapper.toDTO(appointment.get());
        }
        else{
            throw  new InvalidParameterException("There is no appointment with id" + uuid);
        }
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDate(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAllByDate(date);
        return appointments.stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDateAndCenterUUID(LocalDate date, UUID centerId) {
        List<Appointment> appointments = appointmentRepository.findAllByDateAndCenterUuid(date, centerId);
        return appointments.stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointmentsByDonorUUID(UUID donorId) {
        List<Appointment> appointments = appointmentRepository.findAllByDonorUuid(donorId);
        return appointments.stream().map(appointmentMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointment(UUID uuid, AppointmentDTO dto) {
        Optional<Appointment> appointment = appointmentRepository.findById(uuid);
        if(appointment.isPresent()){
            Appointment updatedAppointment = appointment.get();
            if (dto.donor != null) {updatedAppointment.donor = dto.donor;}
            if (dto.center != null) {updatedAppointment.center = dto.center;}
            if (dto.date != null) {updatedAppointment.date = dto.date;}
            if (dto.validated != null) {updatedAppointment.validated = dto.validated;}
            appointmentRepository.save(updatedAppointment);
            return appointmentMapper.toDTO(updatedAppointment);
        }
        else {
            throw new InvalidParameterException("There is no appointment with id" + uuid);
        }
    }


}
