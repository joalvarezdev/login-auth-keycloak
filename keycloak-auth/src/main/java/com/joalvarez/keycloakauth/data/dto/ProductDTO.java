package com.joalvarez.keycloakauth.data.dto;

import com.joalvarez.keycloakauth.data.dto.generals.BaseDTO;

public record ProductDTO(Long id, String name, Double price) implements BaseDTO {
}
