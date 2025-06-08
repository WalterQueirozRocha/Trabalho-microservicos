package com.example.backyardigans.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {
    @NotBlank(message = "login is required")
    private String login;
    
    @NotBlank(message="password is required")
    private  String password;
}
