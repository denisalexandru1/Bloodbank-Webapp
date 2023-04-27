package com.example.WebApp.mapper;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.dto.DoctorDTO;
import com.example.WebApp.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor toDoctor(DoctorDTO dto){
        Doctor doctor = new Doctor();
        doctor.email = dto.email;
        doctor.password = dto.password;
        doctor.firstName = dto.firstName;
        doctor.lastName = dto.lastName;
        doctor.cnp = dto.cnp;
        doctor.center = dto.center;
        return doctor;
    }

    public DoctorDTO toDTO(Doctor doctor){
        DoctorDTO dto = new DoctorDTO();
        dto.uuid = doctor.uuid;
        dto.password = doctor.password;
        dto.email = doctor.email;
        dto.firstName = doctor.firstName;
        dto.lastName = doctor.lastName;
        dto.cnp = doctor.cnp;
        dto.center = doctor.center;
        return dto;
    }

    public AuthDTO toAuthDTO(Doctor doctor) {
        AuthDTO dto = new AuthDTO();
        dto.email = doctor.email;
        dto.password = doctor.password;
        return dto;
    }
}
