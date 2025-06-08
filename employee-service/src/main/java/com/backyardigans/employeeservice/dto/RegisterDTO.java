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
public class RegisterDTO {
    @NotBlank
    private String name;
    
    @NotBlank
    private String login;
    
    @NotBlank
    @Email
    private String email;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String schoolId;
    
    private EmployeeRole role;

    public RegisterDTO(Employee entity) {
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.login = entity.getLogin();
        this.schoolId = entity.getSchoolId();
        this.role = entity.getRole();
    }
}
