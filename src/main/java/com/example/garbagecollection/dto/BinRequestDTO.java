package com.example.garbagecollection.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;


@Data
public class BinRequestDTO {

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private String status;

    private Map<String, Object> sensorData;

    private String locationName;
}