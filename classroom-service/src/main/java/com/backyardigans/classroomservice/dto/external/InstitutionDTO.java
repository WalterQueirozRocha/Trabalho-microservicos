package com.backyardigans.classroomservice.dto.external;

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
}
