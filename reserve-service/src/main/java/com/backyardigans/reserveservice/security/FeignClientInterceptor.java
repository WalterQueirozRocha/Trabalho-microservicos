package com.backyardigans.reserveservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Autowired
    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate template) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            template.header("Authorization", authorizationHeader);
        }
    }
}