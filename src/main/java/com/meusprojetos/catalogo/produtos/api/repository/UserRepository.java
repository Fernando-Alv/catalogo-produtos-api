package com.meusprojetos.catalogo.produtos.api.repository;

import com.meusprojetos.catalogo.produtos.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
