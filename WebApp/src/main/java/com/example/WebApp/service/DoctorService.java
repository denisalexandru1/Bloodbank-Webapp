package com.example.WebApp.service;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.dto.DoctorDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DoctorService {
    DoctorDTO registerDoctor(DoctorDTO dto);

    List<DoctorDTO> getAllDoctors();

    DoctorDTO getDoctorById(UUID uuid);

    DoctorDTO getDoctorByEmail(String email);

    AuthDTO getDoctorByEmailAndPassword(String email, String password);

    DoctorDTO updateDoctor(UUID uuid, DoctorDTO dto);

    void deleteDoctor(UUID uuid);


}
