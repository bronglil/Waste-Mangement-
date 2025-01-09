package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.DriverWithVehicleDTO;
import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.dto.UserResponseDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.service.AdminService;
import com.example.garbagecollection.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Admin", description = "Endpoints for Admin")
@PreAuthorize("hasAuthority('ADMIN')")
class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAllAdmins() {

        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/{id}")
    public User getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @GetMapping("/{name}")
    public List<User> getAdminsByName(@PathVariable String name) {
        return adminService.getAdminsByName(name);
    }

    //    @PostMapping
//    public ResponseEntity<User> createAdmin(@RequestBody UserRequestDTO userRequestDTO) {
//        return ResponseEntity.ok(userService.createAdmin(userRequestDTO));
//    }
    @PutMapping("/{id}")
    public User updateAdmin(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return adminService.updateAdmin(id, userRequestDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/get_drivers")
    @Operation(summary = "all drivers", description = "get a list of all drivers")
    public ResponseEntity<List<DriverWithVehicleDTO>> getAllDrivers() {

        return ResponseEntity.ok(userService.getAllDrivers());
    }

    @GetMapping("/get_driver/{id}")
    @Operation(summary = "single driver by id", description = "get a single driver by id")
    public ResponseEntity<User> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getDriverById(id));
    }

    @PostMapping("/create_driver")
    @Operation(summary = "add a driver", description = "add a new driver")
    public ResponseEntity<UserResponseDTO> createDriver(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PutMapping("/update_driver/{id}")
    @Operation(summary = "update driver by id", description = "get driver by id and update information related to the driver")
    public User updateDriver(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.updateDriver(id, userRequestDTO);
    }

    @DeleteMapping("/remove_driver/{id}")
    @Operation(summary = "delete driver by id", description = "get driver by id and delete the driver")
    public ResponseEntity<Map<String, Object>> deleteDriver(@PathVariable Long id) {
        return userService.deleteDriver(id);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAdminUsers() {
        List<User> adminUsers = userService.getAdminUsers();
        return ResponseEntity.ok(adminUsers);
    }

}
