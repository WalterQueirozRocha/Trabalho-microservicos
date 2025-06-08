package com.backyardigans.institutionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backyardigans.institutionservice.domain.Institution;
import com.backyardigans.institutionservice.dto.InstitutionDTO;
import com.backyardigans.institutionservice.repository.InstitutionRepository;
import com.backyardigans.institutionservice.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository repository;

    @Transactional
    public InstitutionDTO insert(InstitutionDTO dto) {
        Institution entity = new Institution();
        copyDTO(entity, dto);
        entity = repository.save(entity);
        return new InstitutionDTO(entity);

    }

    @Transactional(readOnly = true)
    public InstitutionDTO findById(String id) {
        Institution institution = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));
        return new InstitutionDTO(institution);
    }

    @Transactional(readOnly = true)
    public Page<InstitutionDTO> findAll(Pageable pageable) {
        try {
            Page<Institution> pages = repository.findAll(pageable);
            return pages.map(InstitutionDTO::new);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Institution Not found");
        }
    }

    @Transactional
    public InstitutionDTO update(String id, InstitutionDTO dto) {
        try {
            Institution entity = repository.getReferenceById(id);
            copyDTO(entity, dto);
            entity = repository.save(entity);
            return new InstitutionDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Institution Not Found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(String id) {
        try {
            repository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Institution Not Found");
        }
    }

    private void copyDTO(Institution entity, InstitutionDTO dto) {
        entity.setName(dto.getName());
    }
}
