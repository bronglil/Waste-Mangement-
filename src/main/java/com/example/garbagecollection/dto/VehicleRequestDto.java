package com.example.garbagecollection.dto;

import jakarta.validation.constraints.NotBlank;

public class VehicleRequestDto {

    private Long vehicleId;

    @NotBlank(message = "Vehicle brand must not be blank")
    private String vehicleBrand;
    @NotBlank(message = "Plate number must not be blank")  // Add this line
    private String plateNumber;
    private Long userId;      // Driver's user ID (previously driverId)
    private DriverDto driver;

    // Getters and Setters
    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getUserId() {
        return userId; // Getter for user ID
    }

    public void setUserId(Long userId) {
        this.userId = userId; // Setter for user ID
    }

    public DriverDto getDriver() {
        return driver;
    }

    public void setDriver(DriverDto driver) {
        this.driver = driver;
    }
}