package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.mapper.SecurityMapper;
import com.itau.carros.adapters.out.security.TokenService;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final SecurityMapper securityMapper;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenService tokenService, SecurityMapper mapper) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.securityMapper = mapper;
    }

    public String login(Usuario usuario){
       return tokenService.gerarToken(authenticationManager.authenticate(securityMapper.toAuthenticationToken(usuario)).getName());
    }
}
