package com.example.WebApp.mapper;

import com.example.WebApp.dto.AdminDTO;
import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public Admin toAdmin(AdminDTO dto){
        Admin admin = new Admin();
        admin.email = dto.email;
        admin.password = dto.password;
        return admin;
    }

    public AdminDTO toDTO(Admin admin){
        AdminDTO dto = new AdminDTO();
        dto.email = admin.email;
        return dto;
    }

    public AuthDTO toAuthDTO(Admin admin) {
        AuthDTO dto = new AuthDTO();
        dto.email = admin.email;
        dto.password = admin.password;
        return dto;
    }
}
