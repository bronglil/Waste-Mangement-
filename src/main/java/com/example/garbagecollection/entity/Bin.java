package com.example.garbagecollection.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bins")
public class Bin {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @Setter
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    @Setter
    private LocalDateTime lastUpdated;

    @Setter
    private String sensorData; // Store JSON as a String

    @Setter
    private String locationName; // New field for location name

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(@NotNull double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(@NotNull double longitude) {
        this.longitude = longitude;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getSensorData() {
        return sensorData;
    }

    public String getLocationName() {
        return locationName;
    }





}