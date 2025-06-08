package com.backyardigans.reserveservice.dto.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDTO {
    private String id;
    private String name;
    private Integer capacity;
    private String acronym;
}