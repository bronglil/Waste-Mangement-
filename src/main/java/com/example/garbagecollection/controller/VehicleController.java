package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.VehicleRequestDTO;
import com.example.garbagecollection.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    @Operation(summary = "add vehicle", description = "create a vehicle")
    public ResponseEntity<VehicleRequestDTO> createVehicle(@RequestBody VehicleRequestDTO vehicleDto) {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicleDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get vehicle", description = "get a single vehicle by id")
    public ResponseEntity<VehicleRequestDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    @GetMapping
    @Operation(summary = "get  vehicles", description = "get all vehicles")
    public ResponseEntity<List<VehicleRequestDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PutMapping("/{id}")
    @Operation(summary = "update vehicle", description = "update a single vehicle by id")
    public ResponseEntity<VehicleRequestDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequestDTO vehicleDto) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete vehicle", description = "delete a single vehicle by id")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
