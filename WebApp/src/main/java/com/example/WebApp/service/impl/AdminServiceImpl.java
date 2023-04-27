package com.example.WebApp.service.impl;

import com.example.WebApp.dto.AdminDTO;
import com.example.WebApp.dto.AuthDTO;
import com.example.WebApp.entity.Admin;
import com.example.WebApp.mapper.AdminMapper;
import com.example.WebApp.repository.AdminRepository;
import com.example.WebApp.service.AdminService;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDTO registerAdmin(AdminDTO dto) {
        Admin savedAdmin = adminRepository.save(adminMapper.toAdmin(dto));
        return adminMapper.toDTO(savedAdmin);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(adminMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public AuthDTO getAdminByEmailAndPassword(String email, String password) {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);
        return adminMapper.toAuthDTO(admin);
    }

    @Override
    public AdminDTO updateAdmin(UUID uuid, AdminDTO dto) {
        Optional<Admin> admin = adminRepository.findById(uuid);
        if(admin.isPresent()){
            Admin updatedAdmin = admin.get();
            updatedAdmin.email = dto.email;
            updatedAdmin.password = dto.password;
            adminRepository.save(updatedAdmin);
            return adminMapper.toDTO(updatedAdmin);
        }
        else{
            throw new InvalidParameterException("There is no admin with id" + uuid);
        }
    }
}
