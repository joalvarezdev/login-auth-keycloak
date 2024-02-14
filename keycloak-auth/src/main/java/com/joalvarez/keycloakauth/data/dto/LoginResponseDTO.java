package com.joalvarez.keycloakauth.data.dto;

public record LoginResponseDTO(String accessToken, Long expiresIn, String refreshToken, Long refreshExpiresIn, String tokenType) {}
