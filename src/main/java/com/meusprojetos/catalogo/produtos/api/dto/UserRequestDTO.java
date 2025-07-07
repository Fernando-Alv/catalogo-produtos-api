package com.meusprojetos.catalogo.produtos.api.dto;

public record UserRequestDTO(
        String name,
        String email,
        String password
) { }
