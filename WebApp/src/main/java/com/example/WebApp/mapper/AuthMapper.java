package com.example.WebApp.mapper;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.entity.Doctor;
import com.example.WebApp.entity.Donor;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public AuthDTO toDTO(Donor donor){
        AuthDTO dto = new AuthDTO();
        dto.email = donor.email;
        dto.password = donor.password;
        return dto;
    }
}
