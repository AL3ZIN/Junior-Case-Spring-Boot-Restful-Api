package com.itau.carros.application.core.validations;

import com.itau.carros.application.core.exception.CarroJaCadastradoException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoCarroJaCadastradoTest {

    @InjectMocks
    ValidacaoCarroJaCadastrado validacaoCarroJaCadastrado;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Mock
    Carro carro;


    @Test
    void devePermitirCadastroDeCarroNaoCadastrado(){

        //ARRANGE
        when(carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca()))
                .thenReturn(false);

        //ACT + ASSERT
        assertDoesNotThrow(() -> validacaoCarroJaCadastrado.validar(carro));
        verify(carroRepositoryPort, times(1)).existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca());
    }

    @Test
    void naoDevePermitirCadastroDeCarroJaCadastrado(){

        //ARRANGE
        when(carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca()))
                .thenReturn(true);

        //ACT + ASSERT
        assertThrows(CarroJaCadastradoException.class,() -> validacaoCarroJaCadastrado.validar(carro));
        verify(carroRepositoryPort, times(1)).existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca());
    }
}