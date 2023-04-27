package com.example.WebApp.repository;

import com.example.WebApp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByEmailAndPassword(String email, String password);
}
