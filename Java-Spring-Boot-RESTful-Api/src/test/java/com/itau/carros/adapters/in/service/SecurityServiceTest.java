package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.dto.usuario.CustomUserDetails;
import com.itau.carros.adapters.in.mapper.SecurityMapper;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.in.usuario.GetUsuarioUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {

    @InjectMocks
    private SecurityService securityService;

    @Mock
    private GetUsuarioUseCasePort getUsuarioUseCasePort;

    @Mock
    private SecurityMapper securityMapper;

    @Mock
    private Usuario usuario;

    @Mock
    private CustomUserDetails customUserDetails;

    private String email;

    @BeforeEach
    void setUp(){
        String email = "usuario@teste.com";
    }

    @Test
    void deveConverterECarregarDetalhesDoUsuarioPeloEmail(){

        //ARRANGE
        when(getUsuarioUseCasePort.loadUserByUsername(email)).thenReturn(usuario);
        when(securityMapper.toCustomUserDetails(usuario)).thenReturn(customUserDetails);

        //ACT
        UserDetails result = securityService.loadUserByUsername(email);

        //ASSERT
        verify(getUsuarioUseCasePort, times(1)).loadUserByUsername(email);
        verify(securityMapper, times(1)).toCustomUserDetails(usuario);
        assertEquals(customUserDetails, result, "O objeto UserDetails retornado deve ser o mesmo que o mapper produz");
    }

}