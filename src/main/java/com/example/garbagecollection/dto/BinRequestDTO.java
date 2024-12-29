package com.example.garbagecollection.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;


@Data
public class BinRequestDTO {
    private double latitude;
    private double longitude;
    private int status;
    private Map<String, Object> sensorData;
    private String locationName;
}