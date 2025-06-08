package com.backyardigans.reserveservice.dto.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private String id;
    private String name;
    private String email;
}