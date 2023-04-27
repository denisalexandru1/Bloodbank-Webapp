package com.example.WebApp.service;

import com.example.WebApp.dto.CenterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CenterService {
    CenterDTO registerCenter(CenterDTO dto);
    List<CenterDTO> getAllCenters();
    CenterDTO getCenterById(UUID uuid);
    CenterDTO updateCenter(UUID uuid, CenterDTO dto);
    void deleteCenter(UUID uuid);
}
