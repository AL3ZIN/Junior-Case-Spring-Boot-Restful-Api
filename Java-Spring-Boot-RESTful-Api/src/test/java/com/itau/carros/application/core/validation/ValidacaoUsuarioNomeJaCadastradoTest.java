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
class ValidacaoUsuarioNomeJaCadastradoTest {

    @InjectMocks
    private ValidacaoUsuarioNomeJaCadastrado validacaoUsuarioNomeJaCadastrado;

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private Usuario usuario;

    @Test
    void devePermitirCadastroDeEmailQuandoUsuarioNaoEstaCadastrado(){

        //ARRANGE
        when(usuarioRepositoryPort.existsByNome(usuario.getNome())).thenReturn(false);

        //ACT + ASSERT
        assertDoesNotThrow(() -> validacaoUsuarioNomeJaCadastrado.validar(usuario));
        verify(usuarioRepositoryPort, times(1)).existsByNome(usuario.getNome());
    }

    @Test
    void naoDevePermitirCadastroDeEmailQuandoUsuarioJaEstaCadastrado(){

        //ARRANGE
        when(usuarioRepositoryPort.existsByNome(usuario.getNome())).thenReturn(true);

        //ACT + ASSERT
        var exception = assertThrows(UnicidadeException.class, () -> validacaoUsuarioNomeJaCadastrado.validar(usuario));
        assertEquals("Nome ja cadastrado em nosso banco de dados, por favor escolha outro.", exception.getMessage());
        verify(usuarioRepositoryPort, times(1)).existsByNome(usuario.getNome());
    }
}