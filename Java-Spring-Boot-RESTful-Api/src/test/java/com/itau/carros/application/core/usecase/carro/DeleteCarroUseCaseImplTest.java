package com.itau.carros.application.core.usecase.carro;

import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCarroUseCaseImplTest {

    @InjectMocks
    private DeleteCarroUseCaseImpl deleteCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Test
    void deveChamarDeleteAoExcluirCarro(){

        //ARRANGE
        Long id = 1L;

        //ACT
        deleteCarroUseCase.excluir(id);

        //ASSERT
        verify(carroRepositoryPort, times(1)).delete(id);
    }
}