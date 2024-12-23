package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllDrivers();
    User createDriver(UserRequestDTO userRequestDTO);
    User getDriverById(Long userId);
    List<User> getDriversByName(String name);
    User updateDriver(Long id, UserRequestDTO userRequestDTO);
    void deleteDriver(Long id);
    Optional<User> getDriverByEmail(String mail);
    List<User> getUsersWithoutVehicles();
}
