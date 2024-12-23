package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // Import BCryptPasswordEncoder
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    //    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // Password encoder
// Driver Services
    @Override
    public List<User> getAllDrivers() {
        return userRepository.findByRole(User.UserRole.DRIVER);
    }

    @Override
    public User getDriverById(Long id) {
        return userRepository.findByIdAndRole(id, User.UserRole.DRIVER)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    @Override
    public List<User> getDriversByName(String name) {
        return userRepository.findByFirstNameAndRole(name, User.UserRole.DRIVER);
    }

    public Optional<User> getDriverByEmail(String email) {
        System.out.println("emailemailemailemailemailemail");
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateDriver(Long id, UserRequestDTO userRequestDTO) {
        User user = getDriverById(id); // Ensure the user exists and is a driver
        updateUserFields(user, userRequestDTO);
        return userRepository.save(user);
    }
    @Override
    public void deleteDriver(Long id) {
        User user = getDriverById(id);
        userRepository.delete(user);
    }
    @Override
    public User createDriver(UserRequestDTO userRequestDTO) {
        // Create a new User entity from the DTO
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setContactNumber(userRequestDTO.getContactNumber());
        user.setEmail(userRequestDTO.getEmail());
//        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));  // Encrypt password
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(User.UserRole.DRIVER);  // Default to DRIVER role
        user.setStatus(User.UserStatus.ACTIVE);  // Default to ACTIVE status

        // Save and return the user
        return userRepository.save(user);
    }

    public List<User> getUsersWithoutVehicles() {
        return userRepository.findUsersWithoutVehicles();
    }


    public void updateUserFields(User user, UserRequestDTO dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setContactNumber(dto.getContactNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        // Add more fields as necessary
    }
}
