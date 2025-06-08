package com.backyardigans.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.backyardigans.employeeservice.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	UserDetails findByLogin(String login);
}
