package com.backyardigans.reserveservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.backyardigans.reserveservice.dto.external.ClassroomDTO;

@FeignClient(name = "classroom-service", path = "/classroom")
public interface ClassroomClient {

    @GetMapping("/{id}")
    ClassroomDTO getClassroomById(@PathVariable("id") String id);
}