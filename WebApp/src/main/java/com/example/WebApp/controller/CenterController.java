package com.example.WebApp.controller;

import com.example.WebApp.dto.CenterDTO;
import com.example.WebApp.service.CenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @PostMapping("/center")
    ResponseEntity<CenterDTO> registerCenter(@RequestBody CenterDTO dto){
        CenterDTO registeredCenter = centerService.registerCenter(dto);
        return ResponseEntity.ok(registeredCenter);
    }

    @GetMapping("/center")
    ResponseEntity<List<CenterDTO>> getAllCenters(){
        List<CenterDTO> centers = centerService.getAllCenters();
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/center/{id}")
    ResponseEntity<CenterDTO> getCenterById(@PathVariable("id") UUID uuid){
        CenterDTO center = centerService.getCenterById(uuid);
        return ResponseEntity.ok(center);
    }

    @PutMapping("/center/{id}")
    ResponseEntity<CenterDTO> updateCenter(@PathVariable("id") UUID uuid, @RequestBody CenterDTO dto){
        CenterDTO updatedCenter = centerService.updateCenter(uuid, dto);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/center/{id}")
    ResponseEntity<CenterDTO> deleteCenter(@PathVariable("id") UUID uuid){
        centerService.deleteCenter(uuid);
        return ResponseEntity.ok().build();
    }
}
