package com.example.WebApp.mapper;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentDTO appointmentDTO){
        Appointment appointment = new Appointment();
        appointment.uuid = appointmentDTO.uuid;
        appointment.donor = appointmentDTO.donor;
        appointment.center = appointmentDTO.center;
        appointment.date = appointmentDTO.date;
        appointment.validated = appointmentDTO.validated;
        return appointment;
    }

    public AppointmentDTO toDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.uuid = appointment.uuid;
        appointmentDTO.donor = appointment.donor;
        appointmentDTO.center = appointment.center;
        appointmentDTO.date = appointment.date;
        appointmentDTO.validated = appointment.validated;
        return appointmentDTO;
    }
}
