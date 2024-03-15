package com.itau.carros.adapters.in.hateoas;

import com.itau.carros.adapters.in.dto.carro.CarroListagemResponseDto;
import com.itau.carros.application.core.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

class CarroListagemDtoAssemblerTest {

    CarroListagemResponseDto carroListagemResponseDto;
    CarroListagemDtoAssembler assembler = new CarroListagemDtoAssembler();

    @BeforeEach
    public void setUp(){
        carroListagemResponseDto = new CarroListagemResponseDto(
                1L,
                "chassiteste1",
                "teste",
                "fabricanteteste",
                2024,
                "azul",
                Status.ACTIVATED,
                "placateste"
        );
    }

    @Test
    void testToModel() {

        EntityModel<CarroListagemResponseDto> entityModel = assembler.toModel(carroListagemResponseDto);

        assertAll(
                () -> assertTrue(entityModel.getLinks().hasSize(2)),
                () -> assertTrue(entityModel.getLink("self").isPresent()),
                () -> assertTrue(entityModel.getLink("carros").isPresent()),
                () -> assertEquals(entityModel.getContent().getId(), carroListagemResponseDto.getId())
        );
    }

}