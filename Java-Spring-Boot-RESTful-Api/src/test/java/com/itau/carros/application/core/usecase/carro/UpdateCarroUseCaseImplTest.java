package com.itau.carros.application.core.usecase.carro;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
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
class UpdateCarroUseCaseImplTest {

    @InjectMocks
    private UpdateCarroUseCaseImpl updateCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    private Carro carro;

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
    }

    @Test
    void deveAtualizarStatusDeUmCarro() {

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(carro));

        //ACT + ASSERT
        var response = assertDoesNotThrow(() -> updateCarroUseCase.atualizarStatus(id, Status.DEACTIVATED));
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(response, carro),
                () -> assertEquals(response.getStatus(), Status.DEACTIVATED)
        );
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);

    }

    @Test
    void naoDeveAtualizarStatusDeUmCarro() {

        //ARRANGE
        Long id = 2L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT + ASSERT
        var exception = assertThrows(ConsultaNulaException.class, () -> updateCarroUseCase.atualizarStatus(id, Status.DEACTIVATED));
        assertEquals(String.format("Nenhum carro de id: %d encontrado em nosso banco de dados.", id), exception.getMessage());
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }
}