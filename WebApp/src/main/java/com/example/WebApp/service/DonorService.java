package com.example.WebApp.service;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.dto.DonorDTO;
import com.example.WebApp.entity.Donor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface DonorService {
    DonorDTO registerDonor(DonorDTO dto);

    List<DonorDTO> getAllDonors();

    DonorDTO getDonorById(UUID uuid);

    DonorDTO getDonorByEmail(String email);

    DonorDTO updateDonor(UUID uuid, DonorDTO dto);

    AuthDTO getDonorByEmailAndPassword(String email, String password);

    void deleteDonor(UUID uuid);
}
