package com.example.garbagecollection.controller;

import com.example.garbagecollection.dto.UserRequestDTO;
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

@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "basicAuth")
@Tag(name = "Admin", description = "Endpoints for Admin")
@PreAuthorize("hasAuthority('ADMIN')")
class AdminController {



    @Autowired
    private AdminService adminService;

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
}
