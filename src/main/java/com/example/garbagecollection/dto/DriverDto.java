package com.example.garbagecollection.dto;

import lombok.Data;

@Data
public class DriverDto {
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
    private Long userId;

}