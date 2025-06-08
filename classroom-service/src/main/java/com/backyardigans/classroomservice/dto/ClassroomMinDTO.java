package com.backyardigans.classroomservice.dto;

import com.backyardigans.classroomservice.domain.Classroom;

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
public class ClassroomMinDTO {
    private String id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    @Size(max = 255, message = "description must be less than 255 characters")
    private String description;

    @NotNull(message = "capacity is required")
    @Positive(message = "capacity must be greater than zero")
    private Integer capacity;

    @NotBlank(message = "acronym is required")
    private String acronym;

    @NotNull(message = "isAvailable is required")
    private Boolean isAvailable;

    public ClassroomMinDTO(Classroom entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        capacity = entity.getCapacity();
        acronym = entity.getAcronym();
        isAvailable = entity.isAvailable();
    }

}
