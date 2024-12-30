package com.example.garbagecollection.dto;

import com.example.garbagecollection.entity.User.UserStatus;
import java.util.List;

public class DriverWithVehicleDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private UserStatus status;

    //    private List<VehicleDTO> vehicles;
    private VehicleDTO vehicle;

    // Getters and Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

//    public List<VehicleDTO> getVehicles() {
//        return vehicles;
//    }
//
//    public void setVehicles(List<VehicleDTO> vehicles) {
//        this.vehicles = vehicles;
//    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
} 