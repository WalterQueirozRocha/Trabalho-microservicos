package com.backyardigans.reserveservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.backyardigans.reserveservice.dto.external.EmployeeProfileDTO;
import com.backyardigans.reserveservice.dto.external.TeacherDTO;

@FeignClient(name = "employee-service", path = "/employee")
public interface EmployeeClient {

    @GetMapping("/{id}")
    TeacherDTO getTeacherById(@PathVariable String id);
    
    @GetMapping("/profile")
    EmployeeProfileDTO getProfileByLogin(@RequestHeader("Authorization") String auth);
    
    @GetMapping("/profile/{login}")
    EmployeeProfileDTO findByLogin(@PathVariable String login);
    
}