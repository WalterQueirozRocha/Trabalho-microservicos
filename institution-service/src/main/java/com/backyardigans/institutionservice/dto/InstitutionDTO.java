package com.backyardigans.institutionservice.dto;

import com.backyardigans.institutionservice.domain.Institution;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDTO {

    private String id;
    @NotBlank(message = "name is required")
    private String name;

    public InstitutionDTO(Institution entity) {
        id = entity.getId();
        name = entity.getName();
    }
}
