package com.example.WebApp.controller;

import com.example.WebApp.dto.AdminDTO;
import com.example.WebApp.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    ResponseEntity<AdminDTO> registerAdmin(@RequestBody AdminDTO dto){
        AdminDTO registeredAdmin = adminService.registerAdmin(dto);
        return ResponseEntity.ok(registeredAdmin);
    }

    @PutMapping("/admin/{id}")
    ResponseEntity<AdminDTO> updateAdmin(@PathVariable("id") UUID uuid, @RequestBody AdminDTO dto) {
        AdminDTO updatedAdmin = adminService.updateAdmin(uuid, dto);
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/admin")
    ResponseEntity<List<AdminDTO>> getAllAdmins(){
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
}