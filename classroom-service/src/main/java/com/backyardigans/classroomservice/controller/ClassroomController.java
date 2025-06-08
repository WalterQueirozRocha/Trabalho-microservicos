package com.backyardigans.classroomservice.controller;

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

import com.backyardigans.classroomservice.dto.ClassroomCompleteDTO;
import com.backyardigans.classroomservice.dto.ClassroomDTO;
import com.backyardigans.classroomservice.service.ClassroomService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public ResponseEntity<Page<ClassroomDTO>> findAllClassrooms (Pageable pageable) {
        Page<ClassroomDTO> getAll = classroomService.findAll(pageable);
        return ResponseEntity.ok(getAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDTO> findById(@PathVariable String id) {
        ClassroomDTO dto = classroomService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClassroomCompleteDTO> createClassroom(@RequestBody @Valid ClassroomDTO dto) {
        ClassroomCompleteDTO completeDto = classroomService.insert(dto);
        URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(classroomService.findById(completeDto.getId()))
				.toUri();

        return ResponseEntity.created(uri).body(completeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomCompleteDTO> updateClassroom(@PathVariable String id, @RequestBody @Valid ClassroomDTO entity) {
    	ClassroomCompleteDTO completeDto = classroomService.update(id, entity);
        return ResponseEntity.ok(completeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable String id){
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}