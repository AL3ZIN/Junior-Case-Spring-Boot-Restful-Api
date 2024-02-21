package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CarroInMapperTest {

    @InjectMocks
    CarroInMapper mapper;

    MockSingleton mockSingleton = MockSingleton.getInstance();

    @Test
    void deveConverterCarroDtoParaCarro(){
        CarroDto dto = mockSingleton.getCarroDto();

        Carro model = CarroInMapper.toModel(dto);

        assertEquals(dto.getChassi(), model.getChassi());
    }

    @Test
    void deveConverterCarroFiltroDtoParaCriteriosDeBusca(){
        var dto = mockSingleton.getCarroFiltroDto();

        var model = CarroInMapper.toModel(dto);

        assertEquals(dto.getManufacturer(), model.getManufacturer());
    }

    @Test
    void deveConverterAtributosDtoParaCarroListagemAgrupadaDto(){
        var carros = mockSingleton.getCarroListagemDtoList();
        var manufacturer = "Volvo";

        var model = CarroInMapper.toDto(manufacturer, carros);

        assertEquals(manufacturer, model.getManufacturer());
    }

    @Test
    void deveConverterCarroParaCarroListagemDto(){
        var model = mockSingleton.getCarro();

        var dto = CarroInMapper.toDto(model);

        assertEquals(model.getChassi(), dto.getChassi());
    }

}