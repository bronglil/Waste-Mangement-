package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@SecurityRequirement(name = "basicAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllDrivers() {

        return ResponseEntity.ok(userService.getAllDrivers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getDriverById(id));
    }
    @GetMapping("/{name}")
    public List<User> getDriversByName(@PathVariable String name) {
        return userService.getDriversByName(name);
    }
    @GetMapping("/dash")
    @PreAuthorize("hasAuthority('DRIVER')")
    public String DriverScreen() {
        return "Welcome to the driver homepage";
    }
    @PostMapping
    public ResponseEntity<UserResponseDTO> createDriver(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }
    @PutMapping("/{id}")
    public User updateDriver(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateDriver(id, userRequestDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        userService.deleteDriver(id);
    }


}
