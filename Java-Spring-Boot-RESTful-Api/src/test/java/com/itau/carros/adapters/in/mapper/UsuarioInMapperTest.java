package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioInMapperTest {

    private UsuarioInMapper usuarioInMapper;
    private UsuarioCadastroRequestDto usuarioCadastroRequestDto;
    private UsuarioLoginRequestDto usuarioLoginRequestDto;
    private String token;


    @BeforeEach
    public void setUp() {
        usuarioInMapper = new UsuarioInMapper();
        usuarioCadastroRequestDto = new UsuarioCadastroRequestDto(
                "emailtestecadastrodto@gmail.com",
                "testenomecadastrodto",
                "senhatestecadastrodto"
        );

        usuarioLoginRequestDto = new UsuarioLoginRequestDto(
                "emailtestelogindto@gmail.com",
                "senhatestelogindto"
        );

        token = "testetoken";
    }

    @Test
    void deveConverterUsuarioCadastroRequestDtoParaUsuario() {

        var model = usuarioInMapper.toModel(usuarioCadastroRequestDto);

        assertAll(
                () -> assertEquals(usuarioCadastroRequestDto.getEmail(), model.getEmail()),
                () -> assertEquals(usuarioCadastroRequestDto.getNome(), model.getNome()),
                () -> assertEquals(usuarioCadastroRequestDto.getSenha(), model.getSenha())
        );
    }

    @Test
    void deveConverterUsuarioLoginRequestDtoParaUsuario() {

        var model = usuarioInMapper.toModel(usuarioLoginRequestDto);

        assertAll(
                () -> assertEquals(usuarioLoginRequestDto.getEmail(), model.getEmail()),
                () -> assertEquals(usuarioLoginRequestDto.getSenha(), model.getSenha())
        );
    }

    @Test
    void deveConverterStringParaUsuarioTokenResponseDto() {

        var dto = usuarioInMapper.toDto(token);

        assertEquals(token, dto.getToken());
    }

}