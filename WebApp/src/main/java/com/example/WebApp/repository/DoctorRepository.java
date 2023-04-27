package com.example.WebApp.repository;

import com.example.WebApp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor findByEmail(String email);

    Doctor findByEmailAndPassword(String email, String password);
}
