package com.kollab.authentication.dto;

public record LoginRequest(
        String username,
        String password
) {
}
