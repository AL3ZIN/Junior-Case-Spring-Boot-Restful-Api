package com.itau.carros.application.core.usecase.usuario;

import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.core.validation.ValidacaoUsuarioEmailJaCadastrado;
import com.itau.carros.application.core.validation.ValidacaoUsuarioNomeJaCadastrado;
import com.itau.carros.application.core.validation.Validator;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateUsuarioUseCaseImplTest {

    @InjectMocks
    private CreateUsuarioUseCaseImpl createUsuarioUseCase;

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Spy
    private List<Validator<Usuario>> validacoes = new ArrayList<>();

    @Mock
    private ValidacaoUsuarioEmailJaCadastrado validador1;

    @Mock
    private ValidacaoUsuarioNomeJaCadastrado validador2;

    @Mock
    Usuario usuario;

    @BeforeEach
    void setUp() {
        validacoes.add(validador1);
        validacoes.add(validador2);
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {

        //ACT
        createUsuarioUseCase.cadastrar(usuario);

        //ASSERT
        verify(usuarioRepositoryPort, times(1)).save(usuario);
        then(validador1).should().validar(usuario);
        then(validador2).should().validar(usuario);
    }

}