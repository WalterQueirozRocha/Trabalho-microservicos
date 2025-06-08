package com.backyardigans.institutionservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backyardigans.institutionservice.dto.InstitutionDTO;
import com.backyardigans.institutionservice.service.InstitutionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {

    @Autowired
    private InstitutionService service;

    @GetMapping
    public ResponseEntity<Page<InstitutionDTO>> findAllInstitution (Pageable pageable) {
        Page<InstitutionDTO> getAll = service.findAll(pageable);
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDTO> findById(@PathVariable String id) {
        InstitutionDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<InstitutionDTO> createInstitution(@RequestBody @Valid InstitutionDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(service.findById(dto.getId()))
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionDTO> updateInstitution(@PathVariable String id, @RequestBody @Valid InstitutionDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
