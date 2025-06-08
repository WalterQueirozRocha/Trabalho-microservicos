package com.example.backyardigans.authservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.backyardigans.authservice.externaldto.EmployeeProfileDTO;
import com.example.backyardigans.authservice.security.client.EmployeeClient;
import com.example.backyardigans.authservice.service.exception.ResourceNotFoundException;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private EmployeeClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	EmployeeProfileDTO dto = client.findByLogin(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(
                dto.getLogin(),
                dto.getPassword(),
                List.of(new SimpleGrantedAuthority(dto.getRole()))
            );
    if (user == null) {
        throw new ResourceNotFoundException("User Not Found");
    }
    return user;
}
}