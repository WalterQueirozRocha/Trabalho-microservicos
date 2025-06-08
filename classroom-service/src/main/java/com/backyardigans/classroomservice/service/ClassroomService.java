package com.backyardigans.classroomservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backyardigans.classroomservice.domain.Classroom;
import com.backyardigans.classroomservice.dto.ClassroomCompleteDTO;
import com.backyardigans.classroomservice.dto.ClassroomDTO;
import com.backyardigans.classroomservice.dto.external.InstitutionDTO;
import com.backyardigans.classroomservice.repository.ClassroomRepository;
import com.backyardigans.classroomservice.service.client.InstitutionClient;
import com.backyardigans.institutionservice.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository repository;

    @Autowired
    private InstitutionClient institutionClient;

    @Transactional
    public ClassroomCompleteDTO insert(ClassroomDTO dto) {
        Classroom entity = new Classroom();
        copyDTO(entity, dto);
        entity = repository.save(entity);
        InstitutionDTO institutionDTO = institutionClient.getInstitutionById(entity.getSchoolId());
        return new ClassroomCompleteDTO(entity, institutionDTO);
    }

    @Transactional(readOnly = true)
    public ClassroomDTO findById(String id) {
        Classroom entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom Not Found"));
        InstitutionDTO institutionDTO = institutionClient.getInstitutionById(entity.getSchoolId());
        return new ClassroomDTO(entity, institutionDTO);
    }

    @Transactional(readOnly = true)
    public Page<ClassroomDTO> findAll(Pageable pageable) {
        Page<Classroom> pages = repository.findAll(pageable);
        return pages.map(classroom -> {
            InstitutionDTO institutionDTO = institutionClient.getInstitutionById(classroom.getSchoolId());
            return new ClassroomDTO(classroom, institutionDTO);
        });
    }

    @Transactional
    public ClassroomCompleteDTO update(String id, ClassroomDTO dto) {
        Classroom entity = repository.getReferenceById(id);
        copyDTO(entity, dto);
        entity = repository.save(entity);
        InstitutionDTO institutionDTO = institutionClient.getInstitutionById(entity.getSchoolId());
        return new ClassroomCompleteDTO(entity, institutionDTO);
    }

    @Transactional
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Classroom Not Found");
        }
    }

    private void copyDTO(Classroom entity, ClassroomDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAcronym(dto.getAcronym());
        entity.setCapacity(dto.getCapacity());
        entity.setAvailable(dto.getIsAvailable());
        entity.setSchoolId(dto.getSchoolId());
    }
}