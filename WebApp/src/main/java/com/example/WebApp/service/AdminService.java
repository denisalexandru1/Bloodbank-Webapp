package com.example.WebApp.service;

import com.example.WebApp.dto.AdminDTO;
import com.example.WebApp.dto.AuthDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AdminService {
    AdminDTO registerAdmin(AdminDTO dto);
    List<AdminDTO> getAllAdmins();
    AdminDTO updateAdmin(UUID uuid, AdminDTO dto);

    AuthDTO getAdminByEmailAndPassword(String email, String password);
}
