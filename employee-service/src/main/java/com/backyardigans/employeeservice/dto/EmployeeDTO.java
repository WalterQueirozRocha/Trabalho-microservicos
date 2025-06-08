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
public class EmployeeDTO {
    private String id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "must be an email")
    private String email;
    @NotBlank(message = "login is required")
    private String login;
    private String schoolId;
    private EmployeeRole role;

    public EmployeeDTO(Employee entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.login = entity.getLogin();
        this.schoolId = entity.getSchoolId();
        this.role = entity.getRole();
    }
}