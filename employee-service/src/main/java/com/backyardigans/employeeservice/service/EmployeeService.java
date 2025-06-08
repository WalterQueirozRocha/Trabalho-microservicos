package com.backyardigans.employeeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backyardigans.employeeservice.client.SchoolClient;
import com.backyardigans.employeeservice.domain.Employee;
import com.backyardigans.employeeservice.dto.EmployeeCompleteDTO;
import com.backyardigans.employeeservice.dto.EmployeeDTO;
import com.backyardigans.employeeservice.dto.EmployeeProfileDTO;
import com.backyardigans.employeeservice.dto.RegisterDTO;
import com.backyardigans.employeeservice.dto.SchoolDTO;
import com.backyardigans.employeeservice.dto.UpdatePasswordDTO;
import com.backyardigans.employeeservice.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SchoolClient schoolClient;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public EmployeeDTO register(RegisterDTO newUser) {
        SchoolDTO school = schoolClient.getSchoolById(newUser.getSchoolId());
        if (school == null) {
            throw new EntityNotFoundException("School not found");
        }

        Employee user = new Employee();
        user.setName(newUser.getName());
        user.setLogin(newUser.getLogin());
        user.setEmail(newUser.getEmail());
        user.setSchoolId(newUser.getSchoolId());
        user.setRole(newUser.getRole());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        
        Employee savedUser = repository.save(user);
        return new EmployeeDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public EmployeeCompleteDTO findById(String id) {
        Employee entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));
        return new EmployeeCompleteDTO(entity);
    }
    
    @Transactional(readOnly = true)
   	public EmployeeProfileDTO findByLogin(String login) {
   		Employee entity = (Employee) repository.findByLogin(login);
   		return new EmployeeProfileDTO(entity);
   	}

    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> pages = repository.findAll(pageable);
        return pages.map(EmployeeDTO::new);
    }

    @Transactional
    public EmployeeDTO update(String id, EmployeeDTO dto) {
        Employee entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee Not Found"));
        
        copyDTO(entity, dto);
        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }
    
    public void updatePassword(String login, UpdatePasswordDTO dto) {
    	Employee entity = (Employee) repository.findByLogin(login);
    	if (!new BCryptPasswordEncoder().matches(dto.getOldPassword(), entity.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
    	String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getNewPassword());
    	entity.setPassword(encryptedPassword);
    	entity = repository.save(entity);
    }

    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Employee Not Found");
        }
        repository.deleteById(id);
    }

    private void copyDTO(Employee entity, EmployeeDTO dto) {
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        entity.setName(dto.getName());
        entity.setSchoolId(dto.getSchoolId());
        entity.setRole(dto.getRole());
    }
}