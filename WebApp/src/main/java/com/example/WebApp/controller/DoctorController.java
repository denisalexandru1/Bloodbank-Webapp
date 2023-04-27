package com.example.WebApp.controller;

import com.example.WebApp.dto.DoctorDTO;
import com.example.WebApp.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/doctor")
    ResponseEntity<DoctorDTO> registerDoctor(@RequestBody DoctorDTO dto){
        DoctorDTO registeredDoctor = doctorService.registerDoctor(dto);
        return ResponseEntity.ok(registeredDoctor);
    }

    @GetMapping("/doctor")
    ResponseEntity<List<DoctorDTO>> getAllDoctors(){
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/doctor/{id}")
    ResponseEntity<DoctorDTO> getDoctorById(@PathVariable("id") UUID uuid){
        DoctorDTO doctor = doctorService.getDoctorById(uuid);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("/doctor/email/{email}")
    ResponseEntity<DoctorDTO> getDoctorByEmail(@PathVariable("email") String email){
        DoctorDTO doctor = doctorService.getDoctorByEmail(email);
        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/doctor/{id}")
    ResponseEntity<DoctorDTO> updateDoctor(@PathVariable("id") UUID uuid, @RequestBody DoctorDTO dto){
        DoctorDTO updatedDoctor = doctorService.updateDoctor(uuid, dto);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/doctor/{id}")
    ResponseEntity<DoctorDTO> deleteDoctor(@PathVariable("id") UUID uuid){
        doctorService.deleteDoctor(uuid);
        return ResponseEntity.ok().build();
    }
}
