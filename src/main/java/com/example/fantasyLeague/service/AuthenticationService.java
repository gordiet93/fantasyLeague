package com.example.fantasyLeague.service;

import com.example.fantasyLeague.model.User;
import com.example.fantasyLeague.model.dto.JwtAuthenticationResponse;
import com.example.fantasyLeague.model.dto.SignInRequest;
import com.example.fantasyLeague.model.dto.UserDto;
import com.example.fantasyLeague.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, UserService userService,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse signUp(UserDto userDto) {
        User user = userService.signUp(userDto);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        var jwt = jwtService.generateToken(userRepository.findByUsername(request.getUsername()));
        return new JwtAuthenticationResponse(jwt);
    }
}
