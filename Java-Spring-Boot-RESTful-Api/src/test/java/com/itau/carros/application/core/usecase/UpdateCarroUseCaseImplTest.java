package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCarroUseCaseImplTest {

    @InjectMocks
    UpdateCarroUseCaseImpl updateCarroUseCase;

    @Mock
    CarroRepositoryPort carroRepositoryPort;

    Optional<Carro> optional = MockSingleton.getInstance().getOptionalCarro();

    @Test
    void deveAtualizarStatusDeUmCarro(){

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(optional);

        //ACT + ASSERT
        var response = assertDoesNotThrow(()-> updateCarroUseCase.atualizarStatus(id, Status.DEACTIVATED));
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.DEACTIVATED);
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);

    }

    @Test
    void naoDeveAtualizarStatusDeUmCarro(){

        //ARRANGE
        Long id = 2L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT + ASSERT
        assertThrows(ConsultaNulaException.class,()-> updateCarroUseCase.atualizarStatus(id, Status.DEACTIVATED));
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }
}