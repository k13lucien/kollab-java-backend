package com.kollab.authentication.mapper;

import com.kollab.authentication.dto.UserRequestDTO;
import com.kollab.authentication.dto.UserResponseDTO;
import com.kollab.authentication.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getUsername()
        );
    }

    public User toUser(UserRequestDTO dto) {
        User user = new User();
        user.setFirstname(dto.firstname());
        user.setLastname(dto.lastname());
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        user.setPassword(dto.password());

        return user;
    }

}
