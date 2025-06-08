package com.example.backyardigans.authservice.security.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.backyardigans.authservice.externaldto.EmployeeProfileDTO;

@FeignClient(name = "employee-service", path = "/employee")
public interface EmployeeClient {
    
    @GetMapping("/profile/{login}")
    EmployeeProfileDTO findByLogin(@PathVariable String login);
}