package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.usuario.CustomUserDetails;
import com.itau.carros.application.core.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SecurityMapperTest {

    SecurityMapper securityMapper;
    Usuario usuario;

    @BeforeEach
    public void setUp() {
        securityMapper = new SecurityMapper();
        usuario = new Usuario(
                "emailteste@gmail.com",
                "senhateste",
                "teste"
        );

    }

    @Test
    void deveConverterUsuarioParaUsernamePassowrdAuthenticationToken() {

        var authToken = securityMapper.toAuthenticationToken(usuario);
        var customUserDetails = (CustomUserDetails) authToken.getPrincipal();


        assertAll(
                () -> assertNotNull(authToken),
                () -> assertThat(authToken.getPrincipal()).isInstanceOf(CustomUserDetails.class),
                () -> assertEquals(usuario.getEmail(), customUserDetails.getUsername()),
                () -> assertEquals(usuario.getSenha(), customUserDetails.getPassword())
        );
    }

    @Test
    void deveConverterUsuarioParaCustomUserDetails() {

        var customUserDetails = securityMapper.toCustomUserDetails(usuario);

        assertAll(
                () -> assertEquals(usuario.getEmail(), customUserDetails.getUsername()),
                () -> assertEquals(usuario.getSenha(), customUserDetails.getPassword())
        );
    }

}