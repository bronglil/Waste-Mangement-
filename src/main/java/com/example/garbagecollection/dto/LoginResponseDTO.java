package com.example.garbagecollection.dto;

import com.example.garbagecollection.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    private Long userId;

    private String firstName;

    private String lastName;

    private String contactNumber;

    private String email;

    private User.UserRole userRole;

    private String token;

}
