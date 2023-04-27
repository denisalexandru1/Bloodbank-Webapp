package com.example.WebApp.mapper;

import com.example.WebApp.dto.CenterDTO;
import com.example.WebApp.entity.Center;
import org.springframework.stereotype.Component;

@Component
public class CenterMapper {
    public CenterDTO toDTO(Center center){
        CenterDTO dto = new CenterDTO();
        dto.uuid = center.uuid;
        dto.name = center.name;
        dto.address = center.address;
        dto.startHour = center.startHour;
        dto.endHour = center.endHour;
        dto.maxDonors = center.maxDonors;
        dto.currentDonors = center.currentDonors;
        return dto;
    }

    public Center toCenter(CenterDTO dto){
        Center center = new Center();
        center.uuid = dto.uuid;
        center.name = dto.name;
        center.address = dto.address;
        center.startHour = dto.startHour;
        center.endHour = dto.endHour;
        center.maxDonors = dto.maxDonors;
        center.currentDonors = dto.currentDonors;
        return center;
    }

}
