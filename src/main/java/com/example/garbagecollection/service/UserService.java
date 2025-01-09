package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.*;
import com.example.garbagecollection.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO);
    ResponseEntity<LoginResponseDTO> loginUser(LoginRequestDTO loginRequest);
    User updateDriver(Long id, UserRequestDTO userRequestDTO);
    ResponseEntity<Map<String, Object>> deleteDriver(Long id);
    List<User> getUsersWithoutVehicles();
//    List<User> getAllDrivers();
    List<DriverWithVehicleDTO> getAllDrivers();
    User getDriverById(Long userId);
    List<User> getDriversByName(String name);
    Optional<User> getDriverByEmail(String email);
    Optional<User> getUserByEmail(String email);

    List<User> getAdminUsers();
}
