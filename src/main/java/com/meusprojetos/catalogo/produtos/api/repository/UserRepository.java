package com.meusprojetos.catalogo.produtos.api.repository;

import com.meusprojetos.catalogo.produtos.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
