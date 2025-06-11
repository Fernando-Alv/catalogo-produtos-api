package com.meusprojetos.catalogo.produtos.api.repository;

import com.meusprojetos.catalogo.produtos.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
