package com.example.backyardigans.authservice.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.backyardigans.authservice.externalenum.EmployeeRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
@Table(name = "tb_employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Employee implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(unique = true)
    private String login;
    private String email;
    private String password;
    private String schoolId;

    @Enumerated (EnumType.STRING)
    private EmployeeRole role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == EmployeeRole.ADMINISTRATOR) return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRATOR"), new SimpleGrantedAuthority("ROLE_TEACHER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
    }
    @Override
    public String getUsername() {
        return login;
    }
}