package com.backyardigans.reserveservice.dto;

import java.time.LocalDateTime;

import com.backyardigans.reserveservice.domain.Reserve;
import com.backyardigans.reserveservice.dto.external.ClassroomDTO;
import com.backyardigans.reserveservice.dto.external.TeacherDTO;
import com.backyardigans.reserveservice.enums.ReserveStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveResponseDTO {
    private String id;
    private LocalDateTime initAt;
    private LocalDateTime finishAt;
    private ReserveStatus status;

    private ClassroomDTO classroom;
    private TeacherDTO teacher;

    public ReserveResponseDTO(Reserve entity, TeacherDTO teacherDTO, ClassroomDTO classroomDTO) {
        this.id = entity.getId();
        this.initAt = entity.getInitAt();
        this.finishAt = entity.getFinishAt();
        this.status = entity.getStatus();
        this.teacher = teacherDTO;
        this.classroom = classroomDTO;
    }
}