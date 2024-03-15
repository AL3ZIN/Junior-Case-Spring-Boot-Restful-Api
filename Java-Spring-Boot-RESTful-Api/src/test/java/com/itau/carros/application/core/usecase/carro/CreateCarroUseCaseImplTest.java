package com.itau.carros.application.core.usecase.carro;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.validation.ValidacaoCarroJaCadastrado;
import com.itau.carros.application.core.validation.Validator;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCarroUseCaseImplTest {

    @InjectMocks
    private CreateCarroUseCaseImpl createCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Spy
    private List<Validator<Carro>> validacoes = new ArrayList<>();

    @Mock
   private ValidacaoCarroJaCadastrado validador1;

    Carro carro;

    @BeforeEach
    void setUp() {
        carro = new Carro(
                "chassi1",
                "Fusca",
                "Volkswagen",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placa1"
        );
        validacoes.add(validador1);
    }

    @Test
    void deveCadastrarCarroComSucesso() {

        //ARRANGE
        when(carroRepositoryPort.save(carro)).thenReturn(carro);

        //ACT
        var response = createCarroUseCase.cadastrar(carro);

        //ASSERT
        assertEquals(carro,response);
        verify(carroRepositoryPort, times(1)).save(carro);
        then(validador1).should().validar(carro);
    }

}
