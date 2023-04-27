package com.example.WebApp.controller;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.service.AdminService;
import com.example.WebApp.service.DoctorService;
import com.example.WebApp.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LogInController {
    private final DonorService donorService;
    private final DoctorService doctorService;
    private final AdminService adminService;

    public LogInController(DonorService donorService, DoctorService doctorService, AdminService adminService) {
        this.donorService = donorService;
        this.doctorService = doctorService;
        this.adminService = adminService;
    }

    @PostMapping("/login/donor")
    public ResponseEntity<AuthDTO> loginDonor(@RequestBody AuthDTO auth){
        return ResponseEntity.ok(donorService.getDonorByEmailAndPassword(auth.email, auth.password));
    }

    @PostMapping("/login/doctor")
    public ResponseEntity<AuthDTO> loginDoctor(@RequestBody AuthDTO auth){
        return ResponseEntity.ok(doctorService.getDoctorByEmailAndPassword(auth.email, auth.password));
    }

    @PostMapping("/login/admin")
    public ResponseEntity<AuthDTO> loginAdmin(@RequestBody AuthDTO auth){
        return ResponseEntity.ok(adminService.getAdminByEmailAndPassword(auth.email, auth.password));
    }
}
