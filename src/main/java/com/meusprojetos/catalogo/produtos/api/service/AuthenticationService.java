package com.meusprojetos.catalogo.produtos.api.service;

import com.meusprojetos.catalogo.produtos.api.dto.AuthDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    public String getToken(AuthDTO authDTO);

    public String validateJwtToken(String token);

}
