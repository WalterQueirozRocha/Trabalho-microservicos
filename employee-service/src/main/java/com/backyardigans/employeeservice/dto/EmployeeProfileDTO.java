package com.backyardigans.employeeservice.dto;

import com.backyardigans.employeeservice.domain.Employee;
import com.backyardigans.employeeservice.enums.EmployeeRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private String schoolId;
    private EmployeeRole role;

    public EmployeeProfileDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.login = entity.getLogin();
        this.schoolId = entity.getSchoolId();
        this.role = entity.getRole();
    }
}