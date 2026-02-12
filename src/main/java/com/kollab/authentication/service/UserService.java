package com.kollab.authentication.service;

import com.kollab.authentication.dto.LoginRequest;
import com.kollab.authentication.dto.LoginResponse;
import com.kollab.authentication.dto.UserRequestDTO;
import com.kollab.authentication.dto.UserResponseDTO;
import com.kollab.authentication.mapper.UserMapper;
import com.kollab.authentication.model.User;
import com.kollab.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    public UserResponseDTO register(UserRequestDTO user) {
        User savedUser = mapper.toUser(user);
        savedUser.setPassword(passwordEncoder.encode(savedUser.getPassword()));
        if (savedUser.getUsername() == null) {
            // extract username from email
            String email = savedUser.getEmail();
            if (email != null && email.contains("@")) {
                savedUser.setUsername(email.substring(0, email.indexOf("@")));
            }
        }
        return mapper.toUserResponseDTO(repository.save(savedUser));
    }

    public LoginResponse login(LoginRequest credentials) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(credentials.username());
            return new LoginResponse(token);
        }

        throw new RuntimeException("Invalid username or password");
    }

}