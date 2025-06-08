package com.backyardigans.employeeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.backyardigans.employeeservice.dto.SchoolDTO;

@FeignClient(name = "institution-service", path = "/institution")
public interface SchoolClient {
    
    @GetMapping("/{id}")
    SchoolDTO getSchoolById(@PathVariable String id);
} 