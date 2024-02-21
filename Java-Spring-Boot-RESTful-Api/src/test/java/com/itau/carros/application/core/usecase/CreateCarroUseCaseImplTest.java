package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.validations.ValidacaoCadastroCarro;
import com.itau.carros.application.core.validations.ValidacaoCarroJaCadastrado;
import com.itau.carros.application.ports.in.CreateCarroUseCasePort;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCarroUseCaseImplTest {

    @InjectMocks
    private CreateCarroUseCaseImpl createCarroUseCase;

    @Mock
    private ValidacaoCadastroCarro validador1;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Spy
    private List<ValidacaoCadastroCarro> validacoes = new ArrayList<>();

    @Captor
    private ArgumentCaptor<Carro> carroArgumentCaptor;

    Carro carro = MockSingleton.getInstance().getCarro();

    @BeforeEach
    void setup(){
        //ARRANGE
        given(carroRepositoryPort.save(carro)).willReturn(1L);
        validacoes.add(validador1);
    }

    @Test
    void deveSalvarCarroAoCadastrar() {

        //ACT
        createCarroUseCase.cadastrar(carro);

        //ASSERT
        then(carroRepositoryPort).should().save(carroArgumentCaptor.capture());
        Carro carroSalvo = carroArgumentCaptor.getValue();

        assertEquals(carro.getChassi(), carroSalvo.getChassi());
    }

    @Test
    void deveChamarValidadoresAoCadastrar() {

        //ACT
        createCarroUseCase.cadastrar(carro);

        //ASSERT
        then(validador1).should().validar(carro);
    }
}
