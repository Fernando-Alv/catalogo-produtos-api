package com.meusprojetos.catalogo.produtos.api.controller;

import com.meusprojetos.catalogo.produtos.api.dto.AuthDTO;
import com.meusprojetos.catalogo.produtos.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDTO authDTO) {

        var userAuthenticationToken = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        authenticationManager.authenticate(userAuthenticationToken);

        return authenticationService.getToken(authDTO);
    }
}
