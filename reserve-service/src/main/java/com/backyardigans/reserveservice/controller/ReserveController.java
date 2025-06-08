package com.backyardigans.reserveservice.controller;

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

import com.backyardigans.reserveservice.dto.ReserveDTO;
import com.backyardigans.reserveservice.dto.ReserveResponseDTO;
import com.backyardigans.reserveservice.dto.external.EmployeeProfileDTO;
import com.backyardigans.reserveservice.security.TokenService;
import com.backyardigans.reserveservice.service.ReserveService;
import com.backyardigans.reserveservice.service.client.EmployeeClient;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private EmployeeClient employeeClient;

    @GetMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> findById(@PathVariable String id) {
        ReserveResponseDTO findById = reserveService.findById(id);
        return ResponseEntity.ok(findById);
    }
    

    @GetMapping()
    public ResponseEntity<Page<ReserveResponseDTO>> findAllReserve(Pageable pageable) {
        Page<ReserveResponseDTO> getAll = reserveService.findAll(pageable);
        return ResponseEntity.ok(getAll);
    }
    

    @PostMapping()
    public ResponseEntity<ReserveResponseDTO> createReserve(@RequestHeader("Authorization") String auth, @RequestBody @Valid ReserveDTO dto) {
    	String token = auth.replace("Bearer ", "").trim();
        String login = tokenService.validateToken(token);
        EmployeeProfileDTO employee = employeeClient.findByLogin(login);

        ReserveResponseDTO rdto = reserveService.insert(dto, employee);
        URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(reserveService.findById(rdto.getId()))
				.toUri();
		return ResponseEntity.created(uri).body(rdto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ReserveResponseDTO> updateReserve(@RequestHeader("Authorization") String auth, @PathVariable String id, @RequestBody @Valid ReserveDTO dto) {
    	String token = auth.replace("Bearer ", "").trim();
        String login = tokenService.validateToken(token);
        EmployeeProfileDTO employee = employeeClient.findByLogin(login);

        ReserveResponseDTO rdto = reserveService.update(id, dto, employee);
        return ResponseEntity.ok(rdto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteReserve(@PathVariable String id){
       reserveService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
