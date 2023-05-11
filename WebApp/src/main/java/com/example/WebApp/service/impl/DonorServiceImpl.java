package com.example.WebApp.service.impl;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.dto.DonorDTO;
import com.example.WebApp.entity.Donor;
import com.example.WebApp.mapper.AuthMapper;
import com.example.WebApp.mapper.DonorMapper;
import com.example.WebApp.repository.DonorRepository;
import com.example.WebApp.service.DonorService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;
    private final DonorMapper donorMapper;

    private final AuthMapper authMapper;

    public DonorServiceImpl(DonorRepository donorRepository, DonorMapper donorMapper, AuthMapper authMapper) {
        this.donorRepository = donorRepository;
        this.donorMapper = donorMapper;
        this.authMapper = authMapper;
    }

    @Override
    public DonorDTO registerDonor(DonorDTO dto) {
        if (donorRepository.findByEmail(dto.email) != null) {
            throw new InvalidParameterException("Donor with email " + dto.email + " already exists");
        }
        Donor savedDonor = donorRepository.save(donorMapper.toDonor(dto));
        return donorMapper.toDTO(savedDonor);
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        List<Donor> donors = donorRepository.findAll();
        return donors.stream().map(donorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DonorDTO getDonorById(UUID uuid) {
        Optional<Donor> donor = donorRepository.findById(uuid);
        if(donor.isPresent()){
            return donorMapper.toDTO(donor.get());
        }
        else{
            throw  new InvalidParameterException("There is no donor with id" + uuid);
        }
    }

    @Override
    public DonorDTO getDonorByEmail(String email) {
        Donor donor = donorRepository.findByEmail(email);
        return donorMapper.toDTO(donor);
    }

    @Override
    public AuthDTO getDonorByEmailAndPassword(String email, String password) {
        Donor donor = donorRepository.findByEmailAndPassword(email, password);
        return authMapper.toDTO(donor);
    }

    @Override
    public DonorDTO updateDonor(UUID uuid, DonorDTO dto) {
        Optional<Donor> donor = donorRepository.findById(uuid);
        if(donor.isPresent()){
            Donor updatedDonor = donor.get();
            updatedDonor.email = dto.email;
            updatedDonor.password = dto.password;
            updatedDonor.firstName = dto.firstName;
            updatedDonor.lastName = dto.lastName;
            updatedDonor.bloodType = dto.bloodType;
            updatedDonor.smsReminder = dto.smsReminder;
            updatedDonor.phone = dto.phone;
            donorRepository.save(updatedDonor);
            return donorMapper.toDTO(updatedDonor);
        }
        else{
            throw  new InvalidParameterException("There is no donor with id" + uuid);
        }
    }

    @Override
    public void deleteDonor(UUID uuid) {
        donorRepository.deleteById(uuid);
    }
}
