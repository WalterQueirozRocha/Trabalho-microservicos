package com.example.backyardigans.authservice.externalenum;

public enum EmployeeRole {
    TEACHER("teacher"), ADMINISTRATOR("administrator");

    private String role;

    EmployeeRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
