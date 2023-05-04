package com.example.WebApp.repository;

import com.example.WebApp.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByDate(LocalDate date);
    List<Appointment> findAllByDateAndCenterUuid(LocalDate date, UUID centerUUID);
    List<Appointment> findAllByDonorUuid(UUID donorUUID);
    Page<Appointment> findAllByCenterUuid(UUID centerId, Pageable pageable);
}
