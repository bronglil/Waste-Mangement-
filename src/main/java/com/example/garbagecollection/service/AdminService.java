package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.entity.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {


    List<User> getAllAdmins();
    User getAdminById(Long userId);
    List<User> getAdminsByName(String name);
    Optional<User> getAdminByEmail(String email);
    User updateAdmin(Long id, UserRequestDTO userRequestDTO);
    void deleteAdmin(Long id);

}
