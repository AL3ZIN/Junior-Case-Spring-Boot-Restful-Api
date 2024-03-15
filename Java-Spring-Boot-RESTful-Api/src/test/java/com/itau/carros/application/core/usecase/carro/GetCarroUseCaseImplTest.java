package com.itau.carros.application.core.usecase.carro;

import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCarroUseCaseImplTest {

    @InjectMocks
    private GetCarroUseCaseImpl getCarroUseCase;

    @Mock
    private CarroRepositoryPort carroRepositoryPort;


    private CriteriosDeBusca criteriosDeBusca;

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
    void deveListarCarros() {

        //ARRANGE
        when(carroRepositoryPort.findAllByAtivoTrue()).thenReturn(List.of(carro));

        //ACT
        List<Carro> response = assertDoesNotThrow(() -> getCarroUseCase.listar());

        //ASSERT

        assertAll(
                () -> assertNotNull(response, "Response não deve ser nula."),
                () -> assertEquals(1, response.size(), "O tamanho da response deve ser 1."),
                () -> assertEquals(carro, response.getFirst(), "Response deve corresponder ao esperado.")
        );
        verify(carroRepositoryPort, times(1)).findAllByAtivoTrue();

    }

    @Test
    void naoDeveListarCarrosQuandoNaoExistirCarrosNoBancoDeDados() {

        //ARRANGE
        when(carroRepositoryPort.findAllByAtivoTrue()).thenReturn(Collections.emptyList());


        //ACT + ASSERT
        var exception = assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.listar());
        assertEquals("Não foi encontrado nenhum carro cadastrado em nosso banco de dados.", exception.getMessage());
        verify(carroRepositoryPort, times(1)).findAllByAtivoTrue();
    }

    @Test
    void deveDetalharCarro() {

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.of(carro));

        //ACT + ASSERT
        var response = assertDoesNotThrow(() -> getCarroUseCase.detalhar(id));
        assertAll(
                () -> assertNotNull(response, "Response não deve ser nula."),
                () -> assertEquals(carro, response, "Response deve corresponder ao esperado.")
        );
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void naoDeveDetalharCarroQuandoNulo() {

        //ARRANGE
        Long id = 1L;
        when(carroRepositoryPort.findByIdAndAtivoTrue(id)).thenReturn(Optional.empty());

        //ACT + ASSERT
        ConsultaNulaException exception = assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.detalhar(id));
        assertEquals(String.format("Nenhum carro de id: %d encontrado em nosso banco de dados.", id), exception.getMessage());
        verify(carroRepositoryPort, times(1)).findByIdAndAtivoTrue(id);
    }

    @Test
    void deveFiltrarCarros() {

        //ARRANGE
        when(carroRepositoryPort.findAll(criteriosDeBusca)).thenReturn(List.of(carro));

        //ACT + ASSERT
        var response = assertDoesNotThrow(() -> getCarroUseCase.filtrar(criteriosDeBusca));
        assertAll(
                () -> assertNotNull(response, "Response não deve ser nula."),
                () -> assertEquals(1, response.size(), "O tamanho da response deve ser 1."),
                () -> assertEquals(List.of(carro), response, "Response deve corresponder ao esperado.")
        );
        verify(carroRepositoryPort, times(1)).findAll(criteriosDeBusca);
    }

    @Test
    void naoDeveFiltrarCarrosQuandoCriteriosInvalidos() {

        //ARRANGE
        when(carroRepositoryPort.findAll(criteriosDeBusca)).thenReturn(Collections.emptyList());

        //ACT + ASSERT
        ConsultaNulaException exception = assertThrows(ConsultaNulaException.class, () -> getCarroUseCase.filtrar(criteriosDeBusca));
        assertEquals("Não foi encontrado nenhum carro com esse(s) parametro(s)", exception.getMessage());
        verify(carroRepositoryPort, times(1)).findAll(criteriosDeBusca);
    }

}