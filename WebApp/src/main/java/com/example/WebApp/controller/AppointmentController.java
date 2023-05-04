package com.example.WebApp.controller;

import com.example.WebApp.dto.AppointmentDTO;
import com.example.WebApp.entity.Appointment;
import com.example.WebApp.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/appointment/center/{center}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentsByPaging(@PathVariable("center") UUID centerId, @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "0") int size){
        Page<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByPaging(centerId, page, size);

        return ResponseEntity.ok(appointments.getContent());
    }

    @GetMapping("/appointment/id/{uuid}")
    ResponseEntity<AppointmentDTO> getAppointmentById(@PathVariable("uuid") UUID uuid){
        AppointmentDTO appointment = appointmentService.getAppointmentById(uuid);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/appointment/{date}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentsByDate(@PathVariable("date") LocalDate date){
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDate(date);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/appointment/{date}/{center}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentsByDateAndCenterUUID(@PathVariable("date") LocalDate date, @PathVariable("center") UUID centerId){
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDateAndCenterUUID(date, centerId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/appointment/donor/{donor}")
    ResponseEntity<List<AppointmentDTO>> getAllAppointmentsByDonorUUID(@PathVariable("donor") UUID donorId){
        List<AppointmentDTO> appointments = appointmentService.getAllAppointmentsByDonorUUID(donorId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/appointment/{uuid}")
    ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable("uuid") UUID uuid, @RequestBody AppointmentDTO dto){
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(uuid, dto);
        return ResponseEntity.ok(updatedAppointment);
    }
}
