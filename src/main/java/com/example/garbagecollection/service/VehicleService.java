package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.VehicleRequestDTO;

import java.util.List;

public interface VehicleService {
    VehicleRequestDTO createVehicle(VehicleRequestDTO vehicleDto);

    VehicleRequestDTO getVehicleById(Long id);

    List<VehicleRequestDTO> getAllVehicles();

    VehicleRequestDTO updateVehicle(Long id, VehicleRequestDTO vehicleDto);

    void deleteVehicle(Long id);
}
