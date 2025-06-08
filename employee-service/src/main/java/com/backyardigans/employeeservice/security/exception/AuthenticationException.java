package com.backyardigans.employeeservice.security.exception;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String msg){super(msg); }
}
