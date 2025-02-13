package com.example.garbagecollection.service;
import com.example.garbagecollection.dto.DriverDTO;
import com.example.garbagecollection.dto.VehicleRequestDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.entity.Vehicle;
import com.example.garbagecollection.repository.UserRepository;
import com.example.garbagecollection.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public VehicleRequestDTO createVehicle(VehicleRequestDTO vehicleDto) {
        System.out.println("Creating vehicle with details: " + vehicleDto); // Log the incoming DTO

        if (vehicleDto.getPlateNumber() == null || vehicleDto.getPlateNumber().isEmpty()) {
            throw new IllegalArgumentException("Plate number cannot be null or empty");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleBrand(vehicleDto.getVehicleBrand());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());

        // Check if userId is being set correctly
        if (vehicleDto.getUserId() != null) {
            User user = userRepository.findById(vehicleDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            vehicle.setUser(user);
        } else {
            System.out.println("User ID is null, setting user to null");
            vehicle.setUser(null);
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return  null;
//        return mapToDto(savedVehicle);
    }

    @Override
    public VehicleRequestDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return mapToDto(vehicle);
    }

    @Override
    public List<VehicleRequestDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleRequestDTO updateVehicle(Long id, VehicleRequestDTO vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (vehicleDto.getUserId() != null) { // Use userId here
            User user = userRepository.findById(vehicleDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            vehicle.setUser(user);
        } else {
            vehicle.setUser(null);
        }

        vehicle.setVehicleBrand(vehicleDto.getVehicleBrand());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return mapToDto(updatedVehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicleRepository.delete(vehicle);
    }

    private VehicleRequestDTO mapToDto(Vehicle vehicle) {
        VehicleRequestDTO dto = new VehicleRequestDTO();
        dto.setVehicleId(vehicle.getVehicleId());
        dto.setVehicleBrand(vehicle.getVehicleBrand());
        dto.setPlateNumber(vehicle.getPlateNumber());

        if (vehicle.getUser() != null) {
            User driver = vehicle.getUser(); // Get the driver User entity
            DriverDTO driverDto = new DriverDTO();
            driverDto.setUserId(driver.getId());
            driverDto.setFirstName(driver.getFirstName());
            driverDto.setLastName(driver.getLastName());
            driverDto.setEmail(driver.getEmail());
            driverDto.setContactNumber(driver.getContactNumber());
            dto.setDriver(driverDto);
        } else {
            dto.setDriver(null);
        }
        return dto;
    }
}