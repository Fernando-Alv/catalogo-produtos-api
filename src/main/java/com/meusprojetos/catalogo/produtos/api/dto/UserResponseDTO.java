package com.meusprojetos.catalogo.produtos.api.dto;

import com.meusprojetos.catalogo.produtos.api.enums.UserRole;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        UserRole role
) { }
