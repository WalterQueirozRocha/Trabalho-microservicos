package com.backyardigans.reserveservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.backyardigans.reserveservice.domain.Reserve;
import com.backyardigans.reserveservice.dto.ReserveDTO;
import com.backyardigans.reserveservice.dto.ReserveResponseDTO;
import com.backyardigans.reserveservice.dto.external.ClassroomDTO;
import com.backyardigans.reserveservice.dto.external.EmployeeProfileDTO;
import com.backyardigans.reserveservice.dto.external.TeacherDTO;
import com.backyardigans.reserveservice.enums.ReserveStatus;
import com.backyardigans.reserveservice.repository.ReserveRepository;
import com.backyardigans.reserveservice.service.client.ClassroomClient;
import com.backyardigans.reserveservice.service.client.EmployeeClient;
import com.backyardigans.reserveservice.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReserveService {

    @Autowired
    private ReserveRepository repository;

    @Autowired
    private EmployeeClient employeeClient;
    
    @Autowired
    private ClassroomClient classroomClient;

    @Transactional
    public ReserveResponseDTO insert(ReserveDTO dto, EmployeeProfileDTO employee) {
        List<Reserve> overlappingReserves = repository.findOverlappingReserves(dto.getInitAt(), dto.getFinishAt());
        if (!overlappingReserves.isEmpty()) {
            throw new ResourceNotFoundException("There is already a reservation for this day and time.");
        }

        TeacherDTO teacherDTO = employeeClient.getTeacherById(dto.getTeacherId());
        ClassroomDTO classroomDTO = classroomClient.getClassroomById(dto.getClassroomId());
        Reserve entity = new Reserve();
        
        entity.setInitAt(dto.getInitAt());
        entity.setFinishAt(dto.getFinishAt());
        entity.setTeacherId(dto.getTeacherId());
        entity.setClassroomId(dto.getClassroomId());

        if ("TEACHER".equalsIgnoreCase(employee.getRole())) {
            entity.setStatus(ReserveStatus.PENDING);
        } else {
            entity.setStatus(ReserveStatus.RESERVED);
        }

        entity = repository.save(entity);
        return new ReserveResponseDTO(entity, teacherDTO, classroomDTO);
    }

    @Transactional(readOnly = true)
    public ReserveResponseDTO findById(String id) {
        Reserve entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserve Not Found"));

        TeacherDTO teacherDTO = employeeClient.getTeacherById(entity.getTeacherId());
        ClassroomDTO classroomDTO = classroomClient.getClassroomById(entity.getClassroomId());

        return new ReserveResponseDTO(entity, teacherDTO, classroomDTO);
    }

    @Transactional(readOnly = true)
    public Page<ReserveResponseDTO> findAll(Pageable pageable) {
        Page<Reserve> pages = repository.findAll(pageable);
        return pages.map(reserve -> {
            TeacherDTO teacherDTO = employeeClient.getTeacherById(reserve.getTeacherId());
            ClassroomDTO classroomDTO = classroomClient.getClassroomById(reserve.getClassroomId());
            return new ReserveResponseDTO(reserve, teacherDTO, classroomDTO);
        });
    }

    @Transactional
    public ReserveResponseDTO update(String id, ReserveDTO dto, EmployeeProfileDTO employee) {
        Reserve entity = repository.getReferenceById(id);
        entity.setInitAt(dto.getInitAt());
        entity.setFinishAt(dto.getFinishAt());
        entity.setTeacherId(dto.getTeacherId());
        entity.setClassroomId(dto.getClassroomId());
        
        TeacherDTO teacherDTO = employeeClient.getTeacherById(dto.getTeacherId());
        ClassroomDTO classroomDTO = classroomClient.getClassroomById(dto.getClassroomId());
        

        if ("ROLE_ADMINISTRATOR".equalsIgnoreCase(employee.getRole()) && dto.getStatus() != ReserveStatus.PENDING) {
            throw new IllegalArgumentException("Only administrators can set a status other than PENDING.");
        }

        entity.setStatus(dto.getStatus());
        entity = repository.save(entity);

        return new ReserveResponseDTO(entity, teacherDTO, classroomDTO);
    }

    @Transactional
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Reserve Not Found");
        }
    }
    
    public static String getCurrentAuthorizationToken() {
        ServletRequestAttributes attributes = 
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            String token = attributes.getRequest().getHeader("Authorization");
            if (token != null && !token.isBlank()) return token;
        }

        throw new RuntimeException("Authorization header não encontrado");
    }
}
