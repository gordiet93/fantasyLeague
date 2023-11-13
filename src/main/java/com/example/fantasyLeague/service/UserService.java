package com.example.fantasyLeague.service;

import com.example.fantasyLeague.dto.UserDto;
import com.example.fantasyLeague.model.User;
import com.example.fantasyLeague.model.enums.Role;
import com.example.fantasyLeague.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public User signUp(UserDto dto) {
        String password = bCryptPasswordEncoder.encode(dto.getPassword());
        dto.setPassword(password);
        User user = new User(dto);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
