package com.example.garbagecollection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String contactInfo;

    @NotBlank
    private String assignedVehicle;
}