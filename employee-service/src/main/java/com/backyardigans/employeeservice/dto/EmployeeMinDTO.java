package com.backyardigans.employeeservice.dto;

import com.backyardigans.employeeservice.domain.Employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMinDTO {
    private String id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "must be an email")
    private String email;
    private String schoolId;

    public EmployeeMinDTO(Employee user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.schoolId = user.getSchoolId();
    }
}

