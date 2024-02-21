package com.itau.carros.mock;

import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MockSingleton {

    public static MockSingleton instance;

    private MockSingleton() {
    }

    public static MockSingleton getInstance() {
        if (instance == null) {
            instance = new MockSingleton();
        }
        return instance;
    }

    public Carro getCarro(){
        Carro carro = new Carro();
        carro.setChassi("chassi1");
        carro.setStatus(Status.ACTIVATED);
        carro.setName("Fusca");
        carro.setYear(2023);
        carro.setManufacturer("Volkswagen");
        carro.setColor("Azul");
        carro.setPlaca("placa1");
        return carro;
    }

    public Optional<Carro> getOptionalCarro(){
        return Optional.of(getCarro());
    }
    public List<Carro> getCarroList(){
        return Collections.singletonList(getCarro());
    }

    public CarroDto getCarroDto(){
        CarroDto carroDto = new CarroDto();
        carroDto.setChassi("chassiDto1");
        carroDto.setStatus(Status.RENTED);
        carroDto.setName("Camaro");
        carroDto.setYear(2021);
        carroDto.setManufacturer("Chevrolet");
        carroDto.setColor("Amarelo");
        carroDto.setPlaca("placadto1");
        return carroDto;
    }
}

