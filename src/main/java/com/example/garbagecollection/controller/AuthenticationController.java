package com.example.garbagecollection.controller;
import com.example.garbagecollection.dto.LoginRequestDTO;
import com.example.garbagecollection.dto.LoginResponseDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.repository.UserRepository;
import com.example.garbagecollection.service.UserService;
import com.example.garbagecollection.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Auth", description = "Endpoints for Auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService; // Inject UserService

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil; // Inject JwtUtil

//    @PostMapping("/signup")
//    @Operation(summary = "signup users", description = "add new users")
//    public ResponseEntity<UserResponseDTO> signup(@Valid  @RequestBody UserRequestDTO signupRequest, BindingResult result) {
//        return userService.createUser(signupRequest);
//    }

    @PostMapping("/signup")
    @Operation(summary = "signup users", description = "add new users")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRequestDTO signupRequest) {
        return userService.createUser(signupRequest);
    }
    @PostMapping("/login")
    @Operation(summary = "Login users", description = "Allows access for authorized users to use the system")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
