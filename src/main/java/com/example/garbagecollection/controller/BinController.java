package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.BinRequestDTO;
import com.example.garbagecollection.entity.Bin;
import com.example.garbagecollection.service.BinService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "all bins", description = "get all bins")
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @PostMapping
    @Operation(summary = "add bin", description = "create a bin")
    public ResponseEntity<Bin> createBin(@RequestBody BinRequestDTO binRequestDTO) {
        return ResponseEntity.ok(binService.createBin(binRequestDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "single bin", description = "get a single bin by id")
    public ResponseEntity<Bin> getBinById(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getBinById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update bin", description = "update a single bin by id")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody BinRequestDTO binRequestDTO) {
        return ResponseEntity.ok(binService.updateBin(id, binRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete bin", description = "delete a single bin by id")
    public ResponseEntity<Void> deleteBin(@PathVariable Long id) {
        binService.deleteBin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/location")
    @Operation(summary = "bin location", description = "bin location data")
    public List<Map> getLocationData(@RequestParam String location) {
        return geocodingService.getLocationDataByName(location);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "patch bin", description = "patching a bin")
    public ResponseEntity<Bin> patchBin(@PathVariable Long id, @RequestBody BinRequestDTO binRequestDTO) {
        Bin updatedBin = binService.patchBin(id, binRequestDTO);
        return ResponseEntity.ok(updatedBin);
    }

}
