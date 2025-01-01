package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.BinRequestDTO;
import com.example.garbagecollection.entity.Bin;
import com.example.garbagecollection.service.BinService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.garbagecollection.service.GeocodingService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bins")
@SecurityRequirement(name = "basicAuth")
public class BinController {

    @Autowired
    private BinService binService;

    @Autowired
    private GeocodingService geocodingService;


    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @PostMapping
    public ResponseEntity<Bin> createBin(@RequestBody BinRequestDTO binRequestDTO) {
        return ResponseEntity.ok(binService.createBin(binRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBinById(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getBinById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody BinRequestDTO binRequestDTO) {
        return ResponseEntity.ok(binService.updateBin(id, binRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/location")
    public List<Map> getLocationData(@RequestParam String location) {
        return geocodingService.getLocationDataByName(location);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Bin> patchBin(@PathVariable Long id, @RequestBody BinRequestDTO binRequestDTO) {
        Bin updatedBin = binService.patchBin(id, binRequestDTO);
        return ResponseEntity.ok(updatedBin);
    }

}
