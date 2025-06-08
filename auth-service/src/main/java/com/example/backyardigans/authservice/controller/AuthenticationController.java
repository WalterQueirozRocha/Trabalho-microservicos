package com.example.backyardigans.authservice.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.backyardigans.authservice.domain.Employee;
import com.example.backyardigans.authservice.dto.AuthenticationDTO;
import com.example.backyardigans.authservice.dto.LoginResponseDTO;
import com.example.backyardigans.authservice.security.TokenService;
import com.example.backyardigans.authservice.security.client.EmployeeClient;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO loginUser) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String login = userDetails.getUsername();

        String token = tokenService.generateToken(login);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{login}")
                .buildAndExpand(login)
                .toUri();

        return ResponseEntity.created(uri).body(new LoginResponseDTO(token));
    }
    
    @GetMapping("/debug-user/{login}")
    public ResponseEntity<?> debugUser(@PathVariable String login) {
        var userDetails = employeeClient.findByLogin(login);
        return ResponseEntity.ok(userDetails);
    }
}
