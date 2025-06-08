package com.backyardigans.employeeservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backyardigans.employeeservice.dto.EmployeeCompleteDTO;
import com.backyardigans.employeeservice.dto.EmployeeDTO;
import com.backyardigans.employeeservice.dto.EmployeeProfileDTO;
import com.backyardigans.employeeservice.dto.RegisterDTO;
import com.backyardigans.employeeservice.dto.UpdatePasswordDTO;
import com.backyardigans.employeeservice.security.TokenService;
import com.backyardigans.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@Autowired
	private TokenService tokenService;

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeCompleteDTO> findById(@PathVariable  String id) {
		EmployeeCompleteDTO findById = service.findById(id);
		return ResponseEntity.ok(findById);
	}

	@GetMapping
	public ResponseEntity<Page<EmployeeDTO>> findAllEmployees(Pageable pageable) {
		Page<EmployeeDTO> dtoPages = service.findAll(pageable);
		return ResponseEntity.ok(dtoPages);
	}

	@GetMapping("/profile/{login}")
	public ResponseEntity<EmployeeProfileDTO> findByLogin(@PathVariable String login) {
	    EmployeeProfileDTO employee = service.findByLogin(login);	    
	    return ResponseEntity.ok(employee);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<EmployeeProfileDTO> findByToken(@RequestHeader("Authorization") String auth) {
	    String token = auth.replace("Bearer ", "").trim();
	    String login = tokenService.validateToken(token);
	    EmployeeProfileDTO employee = service.findByLogin(login);	    
	    return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/register")
	public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid RegisterDTO registerUser) {
		EmployeeDTO registeredUser = service.register(registerUser);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(registeredUser.getId())
				.toUri();
		return ResponseEntity.created(uri).body(registeredUser);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable String id, @RequestBody @Valid EmployeeDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(value ="/updatePassword/{login}")
	public ResponseEntity<EmployeeCompleteDTO> updatePassword(@PathVariable String login,@RequestBody @Valid UpdatePasswordDTO dto ) {
	    service.updatePassword(login, dto);
	    return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}