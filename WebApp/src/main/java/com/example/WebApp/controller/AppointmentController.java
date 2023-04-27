package com.example.WebApp.controller;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import com.example.WebApp.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment")
    ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO dto){
        AppointmentDTO createdAppointment = appointmentService.createAppointment(dto);
        return ResponseEntity.ok(createdAppointment);
    }

    @GetMapping("/appointment")
    ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/appointment/{date}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentsByDate(@PathVariable("date") LocalDate date){
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }
}
