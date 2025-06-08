package com.backyardigans.institutionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backyardigans.institutionservice.domain.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, String> {
}
