package com.example.WebApp.service.impl;

import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.dto.DoctorDTO;
import com.example.WebApp.entity.Doctor;
import com.example.WebApp.mapper.DoctorMapper;
import com.example.WebApp.repository.DoctorRepository;
import com.example.WebApp.service.DoctorService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository DoctorRepository;
    private final DoctorMapper DoctorMapper;

    public DoctorServiceImpl(DoctorRepository DoctorRepository, DoctorMapper DoctorMapper) {
        this.DoctorRepository = DoctorRepository;
        this.DoctorMapper = DoctorMapper;
    }

    @Override
    public DoctorDTO registerDoctor(DoctorDTO dto) {
        Doctor savedDoctor = DoctorRepository.save(DoctorMapper.toDoctor(dto));
        return DoctorMapper.toDTO(savedDoctor);
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> Doctors = DoctorRepository.findAll();
        return Doctors.stream().map(DoctorMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorById(UUID uuid) {
        Optional<Doctor> Doctor = DoctorRepository.findById(uuid);
        if(Doctor.isPresent()){
            return DoctorMapper.toDTO(Doctor.get());
        }
        else{
            throw  new InvalidParameterException("There is no Doctor with id" + uuid);
        }
    }

    @Override
    public DoctorDTO getDoctorByEmail(String email) {
        Doctor Doctor = DoctorRepository.findByEmail(email);
        return DoctorMapper.toDTO(Doctor);
    }

    @Override
    public AuthDTO getDoctorByEmailAndPassword(String email, String password) {
        Doctor Doctor = DoctorRepository.findByEmailAndPassword(email, password);
        return DoctorMapper.toAuthDTO(Doctor);
    }

    @Override
    public DoctorDTO updateDoctor(UUID uuid, DoctorDTO dto) {
        Optional<Doctor> doctor = DoctorRepository.findById(uuid);
        if(doctor.isPresent()){
            Doctor updatedDoctor = doctor.get();
            updatedDoctor.email = dto.email;
            updatedDoctor.password = dto.password;
            updatedDoctor.firstName = dto.firstName;
            updatedDoctor.lastName = dto.lastName;
            updatedDoctor.cnp = dto.cnp;
            updatedDoctor.center = dto.center;
            DoctorRepository.save(updatedDoctor);
            return DoctorMapper.toDTO(updatedDoctor);
        }
        else{
            throw  new InvalidParameterException("There is no Doctor with id" + uuid);
        }
    }

    @Override
    public void deleteDoctor(UUID uuid) {
        DoctorRepository.deleteById(uuid);
    }
}
