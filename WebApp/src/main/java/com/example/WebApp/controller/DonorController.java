package com.example.WebApp.controller;

import com.example.WebApp.dto.DonorDTO;
import com.example.WebApp.service.AppointmentService;
import com.example.WebApp.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class DonorController {

    private final DonorService donorService;
    private final AppointmentService appointmentService;

    public DonorController(DonorService donorService, AppointmentService appointmentService) {
        this.donorService = donorService;
        this.appointmentService = appointmentService;
    }

    @PostMapping("/donor/register")
    ResponseEntity<DonorDTO> registerDonor(@RequestBody DonorDTO dto){
        DonorDTO registeredDonor = donorService.registerDonor(dto);
        return ResponseEntity.ok(registeredDonor);
    }

    @GetMapping("/donor")
    ResponseEntity<List<DonorDTO>> getAllDonors(){
        List<DonorDTO> donors = donorService.getAllDonors();
        return ResponseEntity.ok(donors);
    }

    @GetMapping("/donor/{id}")
    ResponseEntity<DonorDTO> getDonorById(@PathVariable("id") UUID uuid){
        DonorDTO donor = donorService.getDonorById(uuid);
        return ResponseEntity.ok(donor);
    }

    @GetMapping("/donor/email/{email}")
    ResponseEntity<DonorDTO> getDonorByEmail(@PathVariable("email") String email){
        DonorDTO donor = donorService.getDonorByEmail(email);
        return ResponseEntity.ok(donor);
    }

    @PutMapping("/donor/{id}")
    ResponseEntity<DonorDTO> updateDonor(@PathVariable("id") UUID uuid, @RequestBody DonorDTO dto){
        DonorDTO updatedDonor = donorService.updateDonor(uuid, dto);
        return ResponseEntity.ok(updatedDonor);
    }

    @DeleteMapping("/donor/{id}")
    ResponseEntity<DonorDTO> deleteDonor(@PathVariable("id") UUID uuid){
        donorService.deleteDonor(uuid);
        appointmentService.deleteAllAppointmentsByDonorUUID(uuid);
        return ResponseEntity.ok().build();
    }
}
