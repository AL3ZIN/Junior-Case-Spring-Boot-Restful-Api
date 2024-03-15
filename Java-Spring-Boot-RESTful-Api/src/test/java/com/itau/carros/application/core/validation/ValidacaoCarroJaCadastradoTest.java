package com.itau.carros.application.core.validation;

import com.itau.carros.application.core.exception.UnicidadeException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoCarroJaCadastradoTest {

    @InjectMocks
    private ValidacaoCarroJaCadastrado validacaoCarroJaCadastrado;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Mock
    private Carro carro;


    @Test
    void devePermitirCadastroQuandoCarroNaoEstaCadastrado(){

        //ARRANGE
        when(carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca()))
                .thenReturn(false);

        //ACT + ASSERT
        assertDoesNotThrow(() -> validacaoCarroJaCadastrado.validar(carro));
        verify(carroRepositoryPort, times(1)).existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca());
    }

    @Test
    void naoDevePermitirCadastroDeCarroQuandoCarroJaEstaCadastrado(){

        //ARRANGE
        when(carroRepositoryPort.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca()))
                .thenReturn(true);

        //ACT + ASSERT
        var exception = assertThrows(UnicidadeException.class,() -> validacaoCarroJaCadastrado.validar(carro));
        assertEquals("Carro ja cadastrado em nosso banco de dados.", exception.getMessage());
        verify(carroRepositoryPort, times(1)).existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(), carro.getPlaca());
    }
}