package com.itau.carros.application.core.validation;

import com.itau.carros.application.core.exception.UnicidadeException;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoUsuarioEmailJaCadastradoTest {

    @InjectMocks
    private ValidacaoUsuarioEmailJaCadastrado validacaoUsuarioEmailJaCadastrado;

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private Usuario usuario;

    @Test
    void devePermitirCadastroDeEmailQuandoUsuarioNaoEstaCadastrado(){

        //ARRANGE
        when(usuarioRepositoryPort.existsByEmail(usuario.getEmail())).thenReturn(false);

        //ACT + ASSERT
        assertDoesNotThrow(() -> validacaoUsuarioEmailJaCadastrado.validar(usuario));
        verify(usuarioRepositoryPort, times(1)).existsByEmail(usuario.getEmail());
    }

    @Test
    void naoDevePermitirCadastroDeEmailQuandoUsuarioJaEstaCadastrado(){

        //ARRANGE
        when(usuarioRepositoryPort.existsByEmail(usuario.getEmail())).thenReturn(true);

        //ACT + ASSERT
        var exception = assertThrows(UnicidadeException.class, () -> validacaoUsuarioEmailJaCadastrado.validar(usuario));
        assertEquals("Email ja cadastrado em nosso banco de dados.", exception.getMessage());
        verify(usuarioRepositoryPort, times(1)).existsByEmail(usuario.getEmail());
    }
}