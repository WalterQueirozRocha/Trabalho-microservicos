package com.backyardigans.reserveservice.domain;

import java.time.LocalDateTime;

import com.backyardigans.reserveservice.enums.ReserveStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_reserve")
@Entity(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private LocalDateTime initAt;
    private LocalDateTime finishAt;

    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    private String classroomId;  // Armazenar o ID da sala de aula
    private String teacherId;    // Armazenar o ID do professor
}
