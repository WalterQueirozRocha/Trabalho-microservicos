package com.backyardigans.classroomservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.backyardigans.classroomservice.dto.external.InstitutionDTO;

@FeignClient(name = "institution-service", path = "/institution")
public interface InstitutionClient {

    @GetMapping("/{id}")
    InstitutionDTO getInstitutionById(@PathVariable String id);
}