package com.backyardigans.classroomservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backyardigans.classroomservice.domain.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, String>{

}
