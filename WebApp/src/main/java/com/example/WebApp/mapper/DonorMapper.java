package com.example.WebApp.mapper;

import com.example.WebApp.dto.DonorDTO;
import com.example.WebApp.entity.Donor;
import org.springframework.stereotype.Component;

@Component
public class DonorMapper {
    public Donor toDonor(DonorDTO dto){
        Donor donor = new Donor();
        donor.email = dto.email;
        donor.password = dto.password;
        donor.firstName = dto.firstName;
        donor.lastName = dto.lastName;
        donor.bloodType = dto.bloodType;
        return donor;
    }

    public DonorDTO toDTO(Donor donor){
        DonorDTO dto = new DonorDTO();
        dto.uuid = donor.uuid;
        dto.email = donor.email;
        dto.firstName = donor.firstName;
        dto.lastName = donor.lastName;
        dto.bloodType = donor.bloodType;
        return dto;
    }
}
