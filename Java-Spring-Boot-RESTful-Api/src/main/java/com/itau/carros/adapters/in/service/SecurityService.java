package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.mapper.SecurityMapper;
import com.itau.carros.application.ports.in.usuario.GetUsuarioUseCasePort;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private final GetUsuarioUseCasePort getUsuarioUseCasePort;
    private final SecurityMapper securityMapper;


    public SecurityService(UsuarioRepositoryPort usuarioRepositoryPort, GetUsuarioUseCasePort getUsuarioUseCasePort, SecurityMapper securityMapper) {
        this.getUsuarioUseCasePort = getUsuarioUseCasePort;
        this.securityMapper = securityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return securityMapper.toCustomUserDetails(getUsuarioUseCasePort.loadUserByUsername(email));
    }
}
