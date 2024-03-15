package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.mapper.SecurityMapper;
import com.itau.carros.adapters.out.security.TokenService;
import com.itau.carros.application.core.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private SecurityMapper securityMapper;

    @Mock
    private Usuario usuario;

    @Mock
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @Test
    void deveGerarTokenParaUsuarioAutenticado(){

        //ARRANGE
        when(securityMapper.toAuthenticationToken(usuario)).thenReturn(usernamePasswordAuthenticationToken);
        when(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
                .thenReturn(usernamePasswordAuthenticationToken);
        when(tokenService.gerarToken(usernamePasswordAuthenticationToken.getName())).thenReturn("token");

        //ACT
        var result = authenticationService.login(usuario);

        //ASSERT
        assertEquals("token", result);
        verify(securityMapper).toAuthenticationToken(usuario);
        verify(authenticationManager).authenticate(usernamePasswordAuthenticationToken);
        verify(tokenService).gerarToken(usernamePasswordAuthenticationToken.getName());
    }

}