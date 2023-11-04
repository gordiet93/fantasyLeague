package com.example.fantasyLeague.controller;

import com.example.fantasyLeague.model.dto.JwtAuthenticationResponse;
import com.example.fantasyLeague.model.dto.SignInRequest;
import com.example.fantasyLeague.model.dto.UserDto;
import com.example.fantasyLeague.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public JwtAuthenticationResponse signUp(@RequestBody UserDto userDto) {
        return authenticationService.signUp(userDto);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signIn(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
