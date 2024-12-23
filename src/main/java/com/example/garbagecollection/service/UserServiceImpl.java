package com.example.garbagecollection.service;

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

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil; // Inject JwtUtil

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
//    @Override
//    User getDriverByName(String name){
//        return "driver name";
//    }
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
    public void deleteDriver(Long id) {
        User user = getDriverById(id);
        userRepository.delete(user);
    }
    @Override
    public ResponseEntity<UserResponseDTO> createUser(UserRequestDTO userRequestDTO){
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
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        if (userRequestDTO.getUserRole() != null) {
            user.setRole(User.UserRole.valueOf(userRequestDTO.getUserRole().toUpperCase()));
        } else {
            throw new RuntimeException("User role is required");
        }
        user.setStatus(User.UserStatus.ACTIVE);
        user.setToken(token);
        userRepository.save(user);
//        preparing user response
        UserResponseDTO user_response = new UserResponseDTO();
        user_response.setFirstName(user.getFirstName());
        user_response.setLastName(user.getLastName());
        user_response.setEmail(user.getEmail());
        user_response.setContactNumber(user.getContactNumber());
        user_response.setUserRole(user.getRole());
        user_response.setToken(user.getToken());

        return  ResponseEntity.ok(user_response);
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

// Preparing Login Response DTO
        LoginResponseDTO login_response = new LoginResponseDTO();
        login_response.setFirstName(user.getFirstName());
        login_response.setLastName(user.getLastName());
        login_response.setEmail(user.getEmail());
        login_response.setContactNumber(user.getContactNumber());
        login_response.setUserRole(user.getRole());
       login_response.setToken(user.getToken());

        return ResponseEntity.ok(login_response);
    }

    public void updateUserFields(User user, UserRequestDTO dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setContactNumber(dto.getContactNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

    }
}
