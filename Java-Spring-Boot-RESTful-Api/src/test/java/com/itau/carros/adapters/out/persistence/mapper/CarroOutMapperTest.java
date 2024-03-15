package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CarroOutMapperTest {

    CarroOutMapper carroOutMapper;

    Carro carro;

    CarroEntity carroEntity;

    @BeforeEach
    void setUp() {
        carroOutMapper = new CarroOutMapper();
        carro = new Carro(
                1L,
                "chassi1",
                "Fusca",
                "Volkswagen",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placa1"
        );

        carroEntity = new CarroEntity(
                "chassientity1",
                "Fuscaentity",
                "Volkswagenentity",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placaentity1"
        );
    }

    @Test
    void deveConverterCarroParaCarroEntity() {

        //ACT
        var entity = carroOutMapper.toEntity(carro);

        //ASSERT
        assertAll(
                () -> assertEquals(carro.getChassi(), entity.getChassi()),
                () -> assertEquals(carro.getName(), entity.getName()),
                () -> assertEquals(carro.getManufacturer(), entity.getManufacturer()),
                () -> assertEquals(carro.getYear(), entity.getYear()),
                () -> assertEquals(carro.getColor(), entity.getColor()),
                () -> assertEquals(carro.getStatus(), entity.getStatus()),
                () -> assertEquals(carro.getPlaca(), entity.getPlaca())
        );

    }

    @Test
    void deveConverterCarroEntityParaCarro() {

        //ACT
        var model = carroOutMapper.toModel(carroEntity);

        //ASSERT
        assertAll(
                () -> assertEquals(carroEntity.getId(), model.getId()),
                () -> assertEquals(carroEntity.getChassi(), model.getChassi()),
                () -> assertEquals(carroEntity.getName(), model.getName()),
                () -> assertEquals(carroEntity.getManufacturer(), model.getManufacturer()),
                () -> assertEquals(carroEntity.getYear(), model.getYear()),
                () -> assertEquals(carroEntity.getColor(), model.getColor()),
                () -> assertEquals(carroEntity.getStatus(), model.getStatus()),
                () -> assertEquals(carroEntity.getPlaca(), model.getPlaca())
        );

    }

}