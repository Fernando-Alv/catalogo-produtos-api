package com.meusprojetos.catalogo.produtos.api.dto;

import com.meusprojetos.catalogo.produtos.api.enums.Department;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        Integer stock,
        Department department
) { }
