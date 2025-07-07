package com.meusprojetos.catalogo.produtos.api.config;

import com.meusprojetos.catalogo.produtos.api.entity.User;
import com.meusprojetos.catalogo.produtos.api.repository.UserRepository;
import com.meusprojetos.catalogo.produtos.api.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractTokenFromHeader(request);

        if(token != null) {
            String email = authenticationService.validateJwtToken(token);
            User user = userRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
