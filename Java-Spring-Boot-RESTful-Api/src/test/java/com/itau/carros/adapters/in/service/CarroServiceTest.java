package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.dto.carro.*;
import com.itau.carros.adapters.in.hateoas.CarroListagemDtoAssembler;
import com.itau.carros.adapters.in.mapper.CarroInMapper;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.in.carro.CreateCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.DeleteCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.GetCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.UpdateCarroUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    private CreateCarroUseCasePort createCarroUseCasePort;

    @Mock
    private GetCarroUseCasePort getCarroUseCasePort;

    @Mock
    private UpdateCarroUseCasePort updateCarroUseCasePort;

    @Mock
    private DeleteCarroUseCasePort deleteCarroUseCasePort;

    @Mock
    private CarroInMapper mapper;

    @Mock
    private CarroListagemDtoAssembler assembler;

    @Mock
    CarroFiltroRequestDto carroFiltroRequestDto;

    @Mock
    CriteriosDeBusca criteriosDeBusca;

    @Mock
    CarroListagemAgrupadaResponseDto carroListagemAgrupadaResponseDto;

    Carro carro;

    CarroRequestDto carroRequestDto;

    CarroListagemResponseDto carroListagemResponseDto;

    CarroUpdateStatusRequestDto carroUpdateStatusRequestDto;

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

        carroRequestDto = new CarroRequestDto(
                "chassiDto1",
                "Camaro",
                "Chevrolet",
                2021,
                "Amarelo",
                Status.RENTED,
                "placadto1"
        );
        carroListagemResponseDto = new CarroListagemResponseDto(
                1L,
                "chassilistagem1",
                "Fusca",
                "Volkswagen",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placalistagem1"
        );

        carroUpdateStatusRequestDto = new CarroUpdateStatusRequestDto(
                1L,
                Status.ACTIVATED
        );

    }


    @Test
    void deveCadastrarCarroComSucesso() {

        //ARRANGE
        when(mapper.toModel(carroRequestDto)).thenReturn(carro);
        when(createCarroUseCasePort.cadastrar(carro)).thenReturn(carro);
        when(mapper.toDto(carro)).thenReturn(carroListagemResponseDto);
        when(assembler.toModel(carroListagemResponseDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        //ACT
        var result = carroService.cadastrar(carroRequestDto);

        //ASSERT
        assertEquals(EntityModel.of(carroListagemResponseDto), result);
        verify(createCarroUseCasePort).cadastrar(carro);
        verify(mapper).toDto(carro);
        verify(assembler).toModel(carroListagemResponseDto);
    }

    @Test
    void deveListarCarrosAgrupadosPorFabricante() {
        //ARRANGE
        when(getCarroUseCasePort.listar()).thenReturn(List.of(carro));
        when(mapper.toDto(carro)).thenReturn(carroListagemResponseDto);
        when(assembler.toModel(carroListagemResponseDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        //ACT
        var result = carroService.listar();

        //ASSERT
        assertAll(
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(result.size(), 1)
        );

        verify(getCarroUseCasePort).listar();
        verify(mapper).toDto(carro);
        verify(assembler).toModel(carroListagemResponseDto);
    }

    @Test
    void deveRetornarDetalhesDoCarroQuandoEncontrado() {
        //ARRANGE
        when(getCarroUseCasePort.detalhar(carro.getId())).thenReturn(carro);
        when(mapper.toDto(carro)).thenReturn(carroListagemResponseDto);
        when(assembler.toModel(carroListagemResponseDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        //ACT
        var result = carroService.detalhar(carro.getId());

        //ASSERT
        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(EntityModel.of(carroListagemResponseDto), result.get())
        );

        verify(getCarroUseCasePort).detalhar(carro.getId());
        verify(mapper).toDto(carro);
        verify(assembler).toModel(carroListagemResponseDto);
    }


    @Test
    void deveFiltrarCarrosConformeRequisicao() {
        //ARRANGE
        when(getCarroUseCasePort.filtrar(criteriosDeBusca)).thenReturn(List.of(carro));
        when(mapper.toModel(carroFiltroRequestDto)).thenReturn(criteriosDeBusca);
        when(mapper.toDto(carro)).thenReturn(carroListagemResponseDto);
        when(assembler.toModel(carroListagemResponseDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        //ACT
        var result = carroService.filtrar(carroFiltroRequestDto);

        //ASSERT
        assertAll(
                () -> assertFalse(result.isEmpty()),
                () -> assertEquals(result.size(), 1),
                () -> assertEquals(List.of(EntityModel.of(carroListagemResponseDto)), result),
                () -> assertEquals(EntityModel.of(carroListagemResponseDto), result.get(0))
        );

        verify(getCarroUseCasePort).filtrar(criteriosDeBusca);
        verify(mapper).toModel(carroFiltroRequestDto);
        verify(mapper).toDto(carro);
        verify(assembler).toModel(carroListagemResponseDto);
    }

    @Test
    void deveAtualizarStatusDoCarro() {
        //ARRANGE
        when(updateCarroUseCasePort.atualizarStatus(carroUpdateStatusRequestDto.getId(), carroUpdateStatusRequestDto.getStatus())).thenReturn(carro);
        when(mapper.toDto(carro)).thenReturn(carroListagemResponseDto);
        when(assembler.toModel(carroListagemResponseDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        //ACT
        var result = carroService.atualizarStatus(carroUpdateStatusRequestDto);

        //ASSERT
        assertEquals(EntityModel.of(carroListagemResponseDto), result);
        verify(updateCarroUseCasePort).atualizarStatus(carroUpdateStatusRequestDto.getId(), carroUpdateStatusRequestDto.getStatus());
        verify(mapper).toDto(carro);
        verify(assembler).toModel(carroListagemResponseDto);
    }

    @Test
    void deveExcluirCarro() {
        //ARRANGE
        doNothing().when(deleteCarroUseCasePort).excluir(carro.getId());

        //ACT
        carroService.excluir(carro.getId());

        //ASSERT
        verify(deleteCarroUseCasePort).excluir(carro.getId());
    }

    @Test
    void deveOrdenarECriarCarroListagemAgrupadaDtoCorretamente() {

        //ARRANGE
        String manufacturer = "Volkswagen";
        List<EntityModel<CarroListagemResponseDto>> carrosModel = List.of(
                EntityModel.of(new CarroListagemResponseDto(1L, "chassi3", "Fusca", manufacturer, 2019, "Vermelho", Status.RENTED, "placa3")),
                EntityModel.of(new CarroListagemResponseDto(2L, "chassi1", "Fusca", manufacturer, 2021, "Azul", Status.ACTIVATED, "placa1")),
                EntityModel.of(new CarroListagemResponseDto(3L, "chassi2", "Gol", manufacturer, 2020, "Amarelo", Status.RENTED, "placa2"))
        );
        when(mapper.toDto(eq(manufacturer), anyList())).thenAnswer(invocation -> {
            List<EntityModel<CarroListagemResponseDto>> sortedList = invocation.getArgument(1);
            return new CarroListagemAgrupadaResponseDto(manufacturer, sortedList);
        });

        //ACT
        var result = carroService.criarCarroListagemAgrupadaDto(manufacturer, carrosModel);

        // Verificações
        assertNotNull(result);
        assertEquals(manufacturer, result.getManufacturer());
        assertEquals(3, result.getCarros().size());

        // Verifica a ordenação: primeiro por nome e, em seguida, por ano decrescente
        assertEquals("Fusca", result.getCarros().get(0).getContent().getName());
        assertEquals(2021, result.getCarros().get(0).getContent().getYear());
        assertEquals("Fusca", result.getCarros().get(1).getContent().getName());
        assertEquals(2019, result.getCarros().get(1).getContent().getYear());
        assertEquals("Gol", result.getCarros().get(2).getContent().getName());
    }


}