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
public class ReserveDTO {
	private String id;
    private LocalDateTime initAt;
    private LocalDateTime finishAt;
    private ReserveStatus status;
    private String classroomId;
    private String teacherId;

    // Construtor para mapear a entidade Reserve para o DTO
    public ReserveDTO(Reserve entity, TeacherDTO teacherDTO, ClassroomDTO classroomDTO) {
        this.id = entity.getId();
        this.initAt = entity.getInitAt();
        this.finishAt = entity.getFinishAt();
        this.status = entity.getStatus();
        this.teacherId = entity.getTeacherId();
        this.classroomId = entity.getClassroomId();
    }

}
