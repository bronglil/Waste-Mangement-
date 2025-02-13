package com.example.garbagecollection.service;

import com.example.garbagecollection.dto.UserRequestDTO;
import com.example.garbagecollection.entity.User;
import com.example.garbagecollection.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllAdmins() {

        return userRepository.findByRole(User.UserRole.ADMIN);
    }
    @Override
    public User getAdminById(Long id) {
        return userRepository.findByIdAndRole(id, User.UserRole.ADMIN)
                .orElseThrow(() -> new RuntimeException("ADMIN not found with ID: " + id));
    }

    @Override
    public List<User> getAdminsByName(String name) {
        return userRepository.findByFirstNameAndRole(name, User.UserRole.ADMIN);
    }

    @Override
    public Optional<User> getAdminByEmail(String email) {
        System.out.println("emailemailemailemailemailemail");
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateAdmin(Long id, UserRequestDTO userRequestDTO) {
        User user = getAdminById(id); // Ensure the user exists and is a admin
        updateUserFields(user, userRequestDTO);
        return userRepository.save(user);
    }

    @Override
    public void deleteAdmin(Long id) {
        User user = getAdminById(id);
        userRepository.delete(user);
    }

    public void updateUserFields(User user, UserRequestDTO dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setContactNumber(dto.getContactNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

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
