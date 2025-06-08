package com.backyardigans.classroomservice.dto;

import com.backyardigans.classroomservice.domain.Classroom;
import com.backyardigans.classroomservice.dto.external.InstitutionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomCompleteDTO {

    private String id;
    private String name;
    private String description;
    private Integer capacity;
    private String acronym;
    private Boolean isAvailable;
    private InstitutionDTO school;

    public ClassroomCompleteDTO(Classroom entity, InstitutionDTO institutionDTO) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.capacity = entity.getCapacity();
        this.acronym = entity.getAcronym();
        this.isAvailable = entity.isAvailable();
        this.school = institutionDTO;
    }
}