package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.carro.CarroFiltroRequestDto;
import com.itau.carros.adapters.in.dto.carro.CarroListagemResponseDto;
import com.itau.carros.adapters.in.dto.carro.CarroRequestDto;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarroInMapperTest {


    private CarroInMapper carroInMapper;
    private CarroRequestDto carroRequestDto;
    private CarroFiltroRequestDto carroFiltroRequestDto;
    private CarroListagemResponseDto carroListagemResponseDto;
    private Carro carro;

    @BeforeEach
    public void setUp() {
        carroInMapper = new CarroInMapper();
        carroRequestDto = new CarroRequestDto(
                "chassiDto1",
                "Camaro",
                "Chevrolet",
                2021,
                "Amarelo",
                Status.RENTED,
                "placadto1"
        );

        carroFiltroRequestDto = new CarroFiltroRequestDto(
                "testefiltro",
                "fabricantetestefiltro",
                2023
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
    void deveConverterCarroDtoParaCarro() {

        Carro model = carroInMapper.toModel(carroRequestDto);

        assertAll(
                () -> assertEquals(carroRequestDto.getChassi(), model.getChassi()),
                () -> assertEquals(carroRequestDto.getName(), model.getName()),
                () -> assertEquals(carroRequestDto.getManufacturer(), model.getManufacturer()),
                () -> assertEquals(carroRequestDto.getYear(), model.getYear()),
                () -> assertEquals(carroRequestDto.getColor(), model.getColor()),
                () -> assertEquals(carroRequestDto.getStatus(), model.getStatus()),
                () -> assertEquals(carroRequestDto.getPlaca(), model.getPlaca())
        );
    }

    @Test
    void deveConverterCarroFiltroDtoParaCriteriosDeBusca() {

        var model = carroInMapper.toModel(carroFiltroRequestDto);

        assertAll(
                () -> assertEquals(carroFiltroRequestDto.getName(), model.getName()),
                () -> assertEquals(carroFiltroRequestDto.getManufacturer(), model.getManufacturer()),
                () -> assertEquals(carroFiltroRequestDto.getYear(), model.getYear())
        );
    }

    @Test
    void deveConverterAtributosParaCarroListagemAgrupadaDto() {
        var manufacturer = "Volvo";
        var carros = List.of(EntityModel.of(carroListagemResponseDto));

        var dto = carroInMapper.toDto(manufacturer, carros);

        assertAll(
                () -> assertEquals(manufacturer, dto.getManufacturer()),
                () -> assertEquals(carros, dto.getCarros())
        );

    }

    @Test
    void deveConverterCarroParaCarroListagemDto() {

        var dto = carroInMapper.toDto(carro);

        assertAll(
                () -> assertEquals(carro.getId(), dto.getId()),
                () -> assertEquals(carro.getChassi(), dto.getChassi()),
                () -> assertEquals(carro.getName(), dto.getName()),
                () -> assertEquals(carro.getManufacturer(), dto.getManufacturer()),
                () -> assertEquals(carro.getYear(), dto.getYear()),
                () -> assertEquals(carro.getColor(), dto.getColor()),
                () -> assertEquals(carro.getStatus(), dto.getStatus()),
                () -> assertEquals(carro.getPlaca(), dto.getPlaca())
        );
    }

}