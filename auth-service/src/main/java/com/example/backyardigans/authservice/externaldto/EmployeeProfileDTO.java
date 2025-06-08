package com.example.backyardigans.authservice.externaldto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileDTO {
    private String id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "must be an email")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "login is required")
    private String login;
    private String role;
    private String schoolId;
}