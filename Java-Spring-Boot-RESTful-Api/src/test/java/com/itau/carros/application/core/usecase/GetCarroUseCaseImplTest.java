package com.itau.carros.application.core.usecase;

import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCarroUseCaseImplTest {

    @InjectMocks
    private GetCarroUseCaseImpl getCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;

    @Mock
    private CriteriosDeBusca criteriosDeBusca;

    List<Carro> carros = MockSingleton.getInstance().getCarroList();

    Optional<Carro> optional = MockSingleton.getInstance().getOptionalCarro();

    List<Carro> carrosEmpty = Collections.emptyList();

    @Test
    void deveListarCarros(){

        //ARRANGE
        when(carroRepositoryPort.findAllByAtivoTrue()).thenReturn(carros);

        //ACT + ASSERT
        List<Carro> response = assertDoesNotThrow(()-> getCarroUseCase.listar());
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(carroRepositoryPort, times(1)).findAllByAtivoTrue();

    }
    @Test
    void naoDeveListarCarros(){

        //ARRANGE
        when(carroRepositoryPort.findAllByAtivoTrue()).thenReturn(carrosEmpty);

        //ACT + ASSERT
        assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.listar());
        verify(carroRepositoryPort, times(1)).findAllByAtivoTrue();
    }

    @Test
    void deveDetalharCarro(){

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(optional);

        //ACT + ASSERT
        var response = assertDoesNotThrow(()-> getCarroUseCase.detalhar(id));
        assertNotNull(response);
        assertEquals(optional.get(), response);
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void naoDeveDetalharCarro(){

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT + ASSERT
        ConsultaNulaException thrown = assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.detalhar(id));
        assertEquals("Nenhum carro de id: 1 encontrado em nosso banco de dados.", thrown.getMessage());
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void deveFiltrarCarros(){

        //ARRANGE
        when(carroRepositoryPort.findAll(criteriosDeBusca)).thenReturn(carros);

        //ACT + ASSERT
        var response = assertDoesNotThrow(()-> getCarroUseCase.filtrar(criteriosDeBusca));
        assertNotNull(response);
        assertEquals(carros, response);
        verify(carroRepositoryPort, times(1)).findAll(criteriosDeBusca);
    }

    @Test
    void naoDeveFiltrarCarros(){


        //ARRANGE
        when(carroRepositoryPort.findAll(criteriosDeBusca)).thenReturn(carrosEmpty);

        //ACT + ASSERT
       assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.filtrar(criteriosDeBusca));
        verify(carroRepositoryPort, times(1)).findAll(criteriosDeBusca);
    }

}