package com.example.movie_api_development.controller;

import com.example.movie_api_development.model.dto.request.AuthenticationRequest;
import com.example.movie_api_development.model.dto.request.RegisterRequest;
import com.example.movie_api_development.model.dto.response.AuthenticationResponse;
import com.example.movie_api_development.service.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ){
        service.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }
}

