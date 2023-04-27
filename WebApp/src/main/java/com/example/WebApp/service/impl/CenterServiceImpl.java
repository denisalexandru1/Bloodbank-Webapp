package com.example.WebApp.service.impl;

import com.example.WebApp.dto.CenterDTO;
import com.example.WebApp.entity.Center;
import com.example.WebApp.mapper.CenterMapper;
import com.example.WebApp.repository.CenterRepository;
import com.example.WebApp.service.CenterService;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final CenterMapper centerMapper;

    public CenterServiceImpl(CenterRepository centerRepository, CenterMapper centerMapper) {
        this.centerRepository = centerRepository;
        this.centerMapper = centerMapper;
    }
    @Override
    public CenterDTO registerCenter(CenterDTO dto) {
        Center savedCenter = centerRepository.save(centerMapper.toCenter(dto));
        return centerMapper.toDTO(savedCenter);
    }

    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = centerRepository.findAll();
        return centers.stream().map(centerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CenterDTO getCenterById(UUID uuid) {
        Optional<Center> center = centerRepository.findById(uuid);
        if(center.isPresent()){
            return centerMapper.toDTO(center.get());
        }
        else{
            throw  new InvalidParameterException("There is no center with id" + uuid);
        }
    }

    @Override
    public CenterDTO updateCenter(UUID uuid, CenterDTO dto) {
        Optional<Center> center = centerRepository.findById(uuid);
        if (center.isPresent()){
            Center updatedCenter = center.get();
            updatedCenter.name = dto.name;
            updatedCenter.address = dto.address;
            updatedCenter.startHour = dto.startHour;
            updatedCenter.endHour = dto.endHour;
            updatedCenter.maxDonors = dto.maxDonors;
            updatedCenter.currentDonors = dto.currentDonors;
            centerRepository.save(updatedCenter);
            return centerMapper.toDTO(updatedCenter);
        }
        else{
            throw new InvalidParameterException("There is no center with id" + uuid);
        }
    }

    @Override
    public void deleteCenter(UUID uuid) {
        centerRepository.deleteById(uuid);
    }
}
