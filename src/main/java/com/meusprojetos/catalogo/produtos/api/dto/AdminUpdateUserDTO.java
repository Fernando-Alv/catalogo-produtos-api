package com.meusprojetos.catalogo.produtos.api.dto;

import com.meusprojetos.catalogo.produtos.api.enums.UserRole;

public record AdminUpdateUserDTO(
        String name,
        String email,
        String password,
        UserRole role
) {
}
