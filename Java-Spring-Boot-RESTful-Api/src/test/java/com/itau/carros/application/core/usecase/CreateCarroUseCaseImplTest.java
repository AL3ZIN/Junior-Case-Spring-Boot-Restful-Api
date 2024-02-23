package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.validations.ValidacaoCadastroCarro;
import com.itau.carros.application.core.validations.ValidacaoCarroJaCadastrado;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCarroUseCaseImplTest {

    @InjectMocks
    private CreateCarroUseCaseImpl createCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Mock
    private List<ValidacaoCarroJaCadastrado> validacoes = new ArrayList<>();

    MockSingleton mockSingleton = MockSingleton.getInstance();


    @Test
    void deveCadastrarCarroComSucesso() {

        var carro = mockSingleton.getCarro();
        when(carroRepositoryPort.save(carro)).thenReturn(carro);

        // Ação
        Carro carroSalvo = createCarroUseCase.cadastrar(carro);

        verify(carroRepositoryPort, times(1)).save(carro);
    }




}
