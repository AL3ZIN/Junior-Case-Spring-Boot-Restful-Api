package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.UsuarioEntity;
import com.itau.carros.application.core.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioOutMapperTest {

    UsuarioOutMapper usuarioOutMapper;

    Usuario usuario;

    UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        usuarioOutMapper = new UsuarioOutMapper();
        usuario = new Usuario(
                "emailteste@gmail.com",
                "senhateste",
                "teste"
        );

        usuarioEntity = new UsuarioEntity(
                "emailtesteentity@gmail.com",
                "senhatesteentity",
                "testeentity"
        );
    }

    @Test
    void deveConverterUsuarioParaUsuarioEntity() {

        //ACT
        var entity = usuarioOutMapper.toEntity(usuario);

        //ASSERT
        assertAll(
                () -> assertEquals(usuario.getEmail(), entity.getEmail()),
                () -> assertEquals(usuario.getSenha(), entity.getSenha()),
                () -> assertEquals(usuario.getNome(), entity.getNome())
        );

    }

    @Test
    void deveConverterUsuarioEntityParaUsuario() {

        //ACT
        var model = usuarioOutMapper.toModel(usuarioEntity);

        //ASSERT
        assertAll(
                () -> assertEquals(usuarioEntity.getEmail(), model.getEmail()),
                () -> assertEquals(usuarioEntity.getSenha(), model.getSenha()),
                () -> assertEquals(usuarioEntity.getNome(), model.getNome())
        );

    }
}