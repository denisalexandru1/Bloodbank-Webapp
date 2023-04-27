package com.example.WebApp.repository;

import com.example.WebApp.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CenterRepository extends JpaRepository<Center, UUID> {
}
