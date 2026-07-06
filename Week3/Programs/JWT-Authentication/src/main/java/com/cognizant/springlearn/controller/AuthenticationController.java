package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.AuthenticationResponse;
import com.cognizant.springlearn.service.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    public AuthenticationController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestHeader("Authorization") String authHeader) {
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        String username = credentials.split(":")[0];

        String token = jwtService.generateToken(username);
        return new AuthenticationResponse(token);
    }
}