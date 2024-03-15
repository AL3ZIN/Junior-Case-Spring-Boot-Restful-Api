package com.itau.carros.application.core.usecase.usuario;

import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUsuarioUseCaseImplTest {

    @InjectMocks
    GetUsuarioUseCaseImpl getUsuarioUseCase;

    @Mock
    UsuarioRepositoryPort usuarioRepositoryPort;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(
                "emailteste@gmail.com",
                "senhateste",
                "teste"
        );
    }

    @Test
    void deveRetornarUsuarioPeloEmail() {

        //ASSERT
        when(usuarioRepositoryPort.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        //ACT + ASSERT
        var response = assertDoesNotThrow(() -> getUsuarioUseCase.loadUserByUsername(usuario.getEmail()));
        assertAll(
                () -> assertNotNull(response, "Response não deve ser nula"),
                () -> assertEquals(usuario, response, "Response deve corresponder ao esperado ")
        );
        verify(usuarioRepositoryPort, times(1)).findByEmail(usuario.getEmail());
    }

    @Test
    void naoDeveRetornarUsuarioQuandoUsuarioNaoExistir() {

        //ASSERT
        when(usuarioRepositoryPort.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());

        //ACT + ASSERT
        var exception = assertThrows(ConsultaNulaException.class,() -> getUsuarioUseCase.loadUserByUsername(usuario.getEmail()));
        assertEquals("Não foi encontrado nenhum usuario com esse email.", exception.getMessage());
        verify(usuarioRepositoryPort, times(1)).findByEmail(usuario.getEmail());
    }

}