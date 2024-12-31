package com.example.garbagecollection.service;

import com.example.garbagecollection.controller.EmailController;
import com.example.garbagecollection.dto.LoginRequestDTO;
import com.example.garbagecollection.dto.LoginResponseDTO;
import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.dto.DriverWithVehicleDTO;
import com.example.garbagecollection.dto.VehicleDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.entity.Vehicle;
import com.example.garbagecollection.exception.UserAlreadyExistsException;
import com.example.garbagecollection.repository.UserRepository;
import com.example.garbagecollection.repository.VehicleRepository;
import com.example.garbagecollection.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // Import BCryptPasswordEncoder
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailController emailController;

    @Override
    public List<User> getAdminUsers() {
        return userRepository.findUsersByRole("ADMIN");
    }

// Driver Services
    @Override
    public List<DriverWithVehicleDTO> getAllDrivers() {
        List<User> drivers = userRepository.findByRole(User.UserRole.DRIVER);
        return drivers.stream().map(driver -> {
            DriverWithVehicleDTO dto = new DriverWithVehicleDTO();
            dto.setUserId(driver.getId());
            dto.setFirstName(driver.getFirstName());
            dto.setLastName(driver.getLastName());
            dto.setEmail(driver.getEmail());
            dto.setContactNumber(driver.getContactNumber());
            dto.setStatus(driver.getStatus());

            Vehicle vehicle = driver.getVehicle();
            if (vehicle != null) {
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setVehicleId(vehicle.getVehicleId());
                vehicleDTO.setVehicleBrand(vehicle.getVehicleBrand());
                vehicleDTO.setPlateNumber(vehicle.getPlateNumber());
                dto.setVehicle(vehicleDTO); // Set the single vehicle
            } else {
                dto.setVehicle(null); // No vehicle associated
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public User getDriverById(Long userId) {
        return userRepository.findByIdAndRole(userId, User.UserRole.DRIVER)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + userId));
    }

    @Override
    public List<User> getDriversByName(String name) {
        return userRepository.findByFirstNameAndRole(name, User.UserRole.DRIVER);
    }

    @Override
    public Optional<User> getDriverByEmail(String email) {
        System.out.println("emailemailemailemailemailemail");
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
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
    public ResponseEntity<Map<String, Object>> deleteDriver(Long id) {
        // Retrieve the driver by ID
        User user = getDriverById(id);

        // Get the associated vehicle and set its user field to null
        Vehicle vehicle = user.getVehicle();
        if (vehicle != null) {
            vehicle.setUser(null); // Unlink the vehicle from the driver
            vehicleRepository.save(vehicle); // Save the vehicle to persist the change
        }

        // Delete the user
        userRepository.delete(user);

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User successfully deleted");
        response.put("userId", id);

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail().toLowerCase());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + userRequestDTO.getEmail() + " already exists");
        }

        User user = new User();
        String token = jwtUtil.generateToken(user.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setContactNumber(userRequestDTO.getContactNumber());
        user.setEmail(userRequestDTO.getEmail());
        String passwordToUse = "";

        // Prepare the response
        UserResponseDTO userResponse = new UserResponseDTO();

        if (userRequestDTO.getUserRole() != null) {
            user.setRole(User.UserRole.valueOf(userRequestDTO.getUserRole().toUpperCase()));
            // If the role is ADMIN, generate a random password
            if (user.getRole() == User.UserRole.ADMIN) {
                passwordToUse = generateRandomPassword();
                userResponse.setAdminPassword(passwordToUse);
                // Generate a random password
            } else {
                // Use the password from the request if not an ADMIN
                passwordToUse = userRequestDTO.getPassword();
            }
        }
        // Set password and encode it
        user.setPassword(passwordEncoder.encode(passwordToUse));
        // If user role is ADMIN, send the generated password via email
        if (user.getRole() == User.UserRole.ADMIN) {
            emailController.sendUserEmail(user.getEmail(), passwordToUse); // Send the generated password
            user.setStatus(User.UserStatus.ACTIVE);
        }
        else{
            user.setStatus(User.UserStatus.PENDING);
        }

        user.setToken(token);
        userRepository.save(user);

        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setContactNumber(user.getContactNumber());
        userResponse.setUserRole(user.getRole());
        if(userResponse.getAdminPassword() != null){
           userResponse.getAdminPassword();
        }
        userResponse.setToken(user.getToken());

        return ResponseEntity.ok(userResponse);
    }

    // Helper method to generate a random 6 to 8 digit password
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        int length = 10 + random.nextInt(3);  // Generate a password length greater than 8
        StringBuilder password = new StringBuilder(length);

        // Define the characters to include in the password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }


    @Override
    public List<User> getUsersWithoutVehicles() {
        return userRepository.findUsersWithoutVehicles();
    }

    @Override
    public ResponseEntity<LoginResponseDTO> loginUser(LoginRequestDTO loginRequest) {
        System.out.println("Login attempt for user");

        // Find user by email
        User user = (User) userRepository.getUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserAlreadyExistsException("User with email: " + loginRequest.getEmail() + " not found"));

        // Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Check if the token is expired
        String token = user.getToken();
        if (jwtUtil.isTokenExpired(token)) {
            // Generate a new token if expired
            token = jwtUtil.generateToken(user.getEmail());
            user.setToken(token);
            userRepository.save(user); // Save the new token to the database
        }

        // Prepare the login response
        LoginResponseDTO loginResponse = new LoginResponseDTO();
        loginResponse.setFirstName(user.getFirstName());
        loginResponse.setLastName(user.getLastName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setContactNumber(user.getContactNumber());
        loginResponse.setUserRole(user.getRole());
        loginResponse.setUserId(user.getId());
        loginResponse.setToken(token); // Return the current (or new) token

        return ResponseEntity.ok(loginResponse);
    }

    public void updateUserFields(User user, UserRequestDTO dto) {
        if(dto.getFirstName() != null){
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null){
            user.setLastName(dto.getLastName());
        }
        if (dto.getContactNumber() != null){
            user.setContactNumber(dto.getContactNumber());
        }
        if(dto.getEmail() != null){
            if (!(user.getEmail().equals(dto.getEmail())))
            {
                user.setEmail(dto.getEmail());
            }

        }
        if (dto.getPassword() != null){
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getUserRole() != null){
            user.setRole(User.UserRole.valueOf(dto.getUserRole().toUpperCase()));
        }
        if (dto.getUserStatus() != null){
            user.setStatus(User.UserStatus.valueOf(dto.getUserStatus().toUpperCase()));
        }


    }

}
