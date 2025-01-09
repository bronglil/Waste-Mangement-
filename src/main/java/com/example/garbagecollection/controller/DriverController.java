package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.dto.DriverWithVehicleDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@SecurityRequirement(name = "basicAuth")
public class DriverController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "all drivers", description = "get a list of all drivers")
    public ResponseEntity<List<DriverWithVehicleDTO>> getAllDrivers() {
        return ResponseEntity.ok(userService.getAllDrivers());
    }
    @GetMapping("/{id}")
    @Operation(summary = "single driver by id", description = "get a single driver by id")
    public ResponseEntity<User> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getDriverById(id));
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "drivers by name", description = "get drivers by name")
    public List<User> getDriversByName(@PathVariable String name) {
        return userService.getDriversByName(name);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createDriver(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update driver by id", description = "get driver by id and update information related to the driver")
    public User updateDriver(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateDriver(id, userRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete driver by id", description = "get driver by id and delete the driver")
    public void deleteDriver(@PathVariable Long id) {
        userService.deleteDriver(id);
    }

    @GetMapping("/available-drivers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsersWithoutVehicles() {
        return userService.getUsersWithoutVehicles();
    }


}
