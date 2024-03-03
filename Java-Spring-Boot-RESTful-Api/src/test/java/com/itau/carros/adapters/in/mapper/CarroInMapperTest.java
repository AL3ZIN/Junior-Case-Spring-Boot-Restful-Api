package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.carro.CarroRequestDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.beans.BeanUtils.copyProperties;

@ExtendWith(MockitoExtension.class)
class CarroInMapperTest {

    @InjectMocks
    CarroInMapper mapper;

    MockSingleton mockSingleton = MockSingleton.getInstance();

    @Test
    void deveConverterCarroDtoParaCarro(){
        CarroRequestDto dto = mockSingleton.getCarroRequestDto();

        Carro model = mapper.toModel(dto);

        assertEquals(dto.getChassi(), model.getChassi());
    }

    @Test
    void deveConverterCarroFiltroDtoParaCriteriosDeBusca(){
        var dto = mockSingleton.getCarroFiltroRequestDto();

        var model = mapper.toModel(dto);

        assertEquals(dto.getManufacturer(), model.getManufacturer());
    }

    @Test
    void deveConverterAtributosDtoParaCarroListagemAgrupadaDto(){
        var carros = mockSingleton.getEntityModelCarroListagemResponseDtoList();
        var manufacturer = "Volvo";

        var model = mapper.toDto(manufacturer, carros);

        assertEquals(manufacturer, model.getManufacturer());
    }

    @Test
    void deveConverterCarroParaCarroListagemDto(){
        var model = mockSingleton.getCarro();

        var dto = mapper.toDto(model);

        assertEquals(model.getChassi(), dto.getChassi());
    }

}