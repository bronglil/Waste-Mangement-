package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.LoginRequestDTO;
import com.example.garbagecollection.dto.LoginResponseDTO;
import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
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
    List<User> getAllDrivers();
    User getDriverById(Long userId);
//    User getDriverByName(String name);
    List<User> getDriversByName(String name);
    Optional<User> getDriverByEmail(String email);

}
