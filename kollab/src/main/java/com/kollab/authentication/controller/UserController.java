package com.kollab.authentication.controller;

import com.kollab.authentication.dto.LoginRequest;
import com.kollab.authentication.dto.LoginResponse;
import com.kollab.authentication.dto.UserRequestDTO;
import com.kollab.authentication.dto.UserResponseDTO;
import com.kollab.authentication.model.User;
import com.kollab.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public UserResponseDTO register(
            @RequestBody UserRequestDTO user
    ) {
        return service.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest credentials
    ) {
        return service.login(credentials);
    }

}
