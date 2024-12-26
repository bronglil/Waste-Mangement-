package com.example.garbagecollection.service;

import com.example.garbagecollection.controller.EmailController;
import com.example.garbagecollection.dto.LoginRequestDTO;
import com.example.garbagecollection.dto.LoginResponseDTO;
import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.repository.UserRepository;
import com.example.garbagecollection.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // Import BCryptPasswordEncoder
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailController emailController;

// Driver Services
    @Override
    public List<User> getAllDrivers() {
        return userRepository.findByRole(User.UserRole.DRIVER);
    }

    @Override
    public User getDriverById(Long userId) {
        return userRepository.findByIdAndRole(userId, User.UserRole.DRIVER)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + userId));
    }

    @Override
    public Optional<User> getDriverByEmail(String email) {
        System.out.println("emailemailemailemailemailemail");
        return userRepository.findByEmail(email);
    }
    @Override
    public List<User> getDriversByName(String name) {
        return userRepository.findByFirstNameAndRole(name, User.UserRole.DRIVER);
    }
    @Override
    public User updateDriver(Long id, UserRequestDTO userRequestDTO) {
        User user = getDriverById(id); // Ensure the user exists and is a driver
        updateUserFields(user, userRequestDTO);
        return userRepository.save(user);
    }
    @Override
    public ResponseEntity<Map<String, Object>> deleteDriver(Long id) {
        User user = getDriverById(id);
        userRepository.delete(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User successfully deleted");
        response.put("userId", id);

        return ResponseEntity.ok(response);
    }


    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        String token = jwtUtil.generateToken(user.getEmail());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setContactNumber(userRequestDTO.getContactNumber());
        user.setEmail(userRequestDTO.getEmail());

        // If the role is ADMIN, generate a random password
        String passwordToUse;
        if (userRequestDTO.getUserRole() != null) {
            user.setRole(User.UserRole.valueOf(userRequestDTO.getUserRole().toUpperCase()));
            if (user.getRole() == User.UserRole.ADMIN) {
                passwordToUse = generateRandomPassword(); // Generate a random password
            } else {
                // Use the password from the request if not an ADMIN
                passwordToUse = userRequestDTO.getPassword();
            }
        } else {
            throw new RuntimeException("User role is required");
        }
//        user.setStatus(User.UserStatus.ACTIVE);
        user.setToken(token);
        userRepository.save(user);

        // If user role is ADMIN, send the generated password via email
        if (user.getRole() == User.UserRole.ADMIN) {
            emailController.sendUserEmail(user.getEmail(), passwordToUse); // Send the generated password
        }

        // Prepare the response
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setContactNumber(user.getContactNumber());
        userResponse.setUserRole(user.getRole());
        userResponse.setToken(user.getToken());

        return ResponseEntity.ok(userResponse);
    }

    // Helper method to generate a random 6 to 8 digit password
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        int length = 6 + random.nextInt(3);  // Generate a password length between 6 and 8
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
    public ResponseEntity<LoginResponseDTO> loginUser(LoginRequestDTO loginRequest){
        System.out.println("Login attempt for user");

        // Find user by email
        User user = (User) userRepository.getDriverByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        System.out.println("user: " + user);

        LoginResponseDTO login_response = new LoginResponseDTO();
        login_response.setFirstName(user.getFirstName());
        login_response.setLastName(user.getLastName());
        login_response.setEmail(user.getEmail());
        login_response.setContactNumber(user.getContactNumber());
        login_response.setUserRole(user.getRole());
        login_response.setUserId(user.getId());
       login_response.setToken(user.getToken());

        return ResponseEntity.ok(login_response);
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
       if (dto.getUserStatus() != null){
           user.setPassword(dto.getPassword());
       }
       if (dto.getUserRole() != null){
           user.setRole(User.UserRole.valueOf(dto.getUserRole().toUpperCase()));
       }
        if (dto.getUserStatus() != null){
            user.setStatus(User.UserStatus.valueOf(dto.getUserStatus().toUpperCase()));
        }


    }
}
