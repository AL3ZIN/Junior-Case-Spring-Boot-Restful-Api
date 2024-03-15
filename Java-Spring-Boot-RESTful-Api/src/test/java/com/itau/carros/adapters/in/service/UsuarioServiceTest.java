package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioTokenResponseDto;
import com.itau.carros.adapters.in.mapper.UsuarioInMapper;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.in.usuario.CreateUsuarioUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {


    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private CreateUsuarioUseCasePort createUsuarioUseCasePort;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioInMapper mapper;

    @Mock
    private UsuarioLoginRequestDto usuarioLoginRequestDto;

    @Mock
    private Usuario usuario;

    private UsuarioTokenResponseDto usuarioTokenResponseDto;

    private String token;

    private UsuarioCadastroRequestDto usuarioCadastroRequestDto;

    @BeforeEach
    void setUp() {
        token = "testetoken";
        usuarioTokenResponseDto = new UsuarioTokenResponseDto(token);
        usuarioCadastroRequestDto = new UsuarioCadastroRequestDto(
                "emailteste@gmail.com",
                "testenome",
                "senhateste"
        );
    }

    @Test
    void deveRealizarLoginComSucesso() {

        //ARRANGE
        when(mapper.toModel(usuarioLoginRequestDto)).thenReturn(usuario);
        when(authenticationService.login(usuario)).thenReturn(token);
        when(mapper.toDto(token)).thenReturn(usuarioTokenResponseDto);

        //ACT
        var result = usuarioService.login(usuarioLoginRequestDto);

        // Assert
        assertEquals(usuarioTokenResponseDto, result);
        verify(mapper).toModel(usuarioLoginRequestDto);
        verify(authenticationService).login(usuario);
        verify(mapper).toDto(token);
    }

    @Test
    void deveCadastrarUsuarioComSenhaCodificada() {

        //ARRANGE
        when(mapper.toModel(usuarioCadastroRequestDto)).thenReturn(usuario);

        //ACT
        usuarioService.cadastrar(usuarioCadastroRequestDto);

        //ASSERT
        verify(mapper).toModel(usuarioCadastroRequestDto);
        verify(createUsuarioUseCasePort).cadastrar(usuario);
    }


}
