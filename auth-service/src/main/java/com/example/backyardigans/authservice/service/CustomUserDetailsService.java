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

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            EmployeeProfileDTO user = employeeClient.findByLogin(login);
            return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}