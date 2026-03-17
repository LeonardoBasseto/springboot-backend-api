package com.leonardo.backend_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.leonardo.backend_api.dto.AcessDTO;
import com.leonardo.backend_api.dto.AuthenticationDTO;
import com.leonardo.backend_api.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDto) {
        try {
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl userAuthenticated = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateJwtToken(userAuthenticated);

            return new AcessDTO(token);

        } catch (BadCredentialsException e) {
            return new AcessDTO("Acesso negado");
        }
    }
}