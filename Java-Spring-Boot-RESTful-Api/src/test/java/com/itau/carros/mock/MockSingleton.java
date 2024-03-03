package com.itau.carros.mock;

import com.itau.carros.adapters.in.dto.carro.*;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import org.springframework.hateoas.EntityModel;

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

    public CarroRequestDto getCarroRequestDto(){
        CarroRequestDto carroRequestDto = new CarroRequestDto();
        carroRequestDto.setChassi("chassiDto1");
        carroRequestDto.setStatus(Status.RENTED);
        carroRequestDto.setName("Camaro");
        carroRequestDto.setYear(2021);
        carroRequestDto.setManufacturer("Chevrolet");
        carroRequestDto.setColor("Amarelo");
        carroRequestDto.setPlaca("placadto1");
        return carroRequestDto;
    }

    public List<CarroRequestDto> getCarroDtoList(){
        return Collections.singletonList(getCarroRequestDto());
    }

    public CarroListagemAgrupadaResponseDto getCarroListagemAgrupadaResponseDto(){
        CarroListagemAgrupadaResponseDto carroListagemAgrupadaResponseDto = new CarroListagemAgrupadaResponseDto();
        carroListagemAgrupadaResponseDto.setCarros(getEntityModelCarroListagemResponseDtoList());
        carroListagemAgrupadaResponseDto.setManufacturer(getCarroRequestDto().getManufacturer());
        return carroListagemAgrupadaResponseDto;
    }
    public List<CarroListagemAgrupadaResponseDto> getCarroListagemAgrupadaResponseDtoList(){
        return Collections.singletonList(getCarroListagemAgrupadaResponseDto());
    }

    public CarroFiltroRequestDto getCarroFiltroRequestDto(){
        CarroFiltroRequestDto carroFiltroRequestDto = new CarroFiltroRequestDto();
        carroFiltroRequestDto.setName("Fusca");
        carroFiltroRequestDto.setManufacturer("Lamborghini");
        carroFiltroRequestDto.setYear(2024);
        return carroFiltroRequestDto;
    }

    public CarroUpdateStatusRequestDto getCarroUpdateStatusRequestDto() {
        var carroUpdateStatusRequestDto = new CarroUpdateStatusRequestDto();
        carroUpdateStatusRequestDto.setStatus(Status.DEACTIVATED);
        carroUpdateStatusRequestDto.setId(1L);
        return carroUpdateStatusRequestDto;
    }

    public CarroListagemResponseDto getCarroListagemResponseDto(){
        var carroListagemResponseDto = new CarroListagemResponseDto();
        carroListagemResponseDto.setId(1L);
        carroListagemResponseDto.setChassi("chassiDto1");
        carroListagemResponseDto.setStatus(Status.RENTED);
        carroListagemResponseDto.setName("Camaro");
        carroListagemResponseDto.setYear(2021);
        carroListagemResponseDto.setManufacturer("Chevrolet");
        carroListagemResponseDto.setColor("Amarelo");
        carroListagemResponseDto.setPlaca("placadto1");
        return carroListagemResponseDto;
    }
    public EntityModel<CarroListagemResponseDto> getEntityModelCarroListagemResponseDto(){
        return EntityModel.of(getCarroListagemResponseDto());
    }


    public List<CarroListagemResponseDto> getCarroListagemResponseDtoList() {
        return Collections.singletonList(getCarroListagemResponseDto());
    }

    public List<EntityModel<CarroListagemResponseDto>> getEntityModelCarroListagemResponseDtoList() {
        return Collections.singletonList(getEntityModelCarroListagemResponseDto());
    }

    public Optional<EntityModel<CarroListagemResponseDto>> getOptionalEntityModelCarroListagemResponseDto(){
        return Optional.of(getEntityModelCarroListagemResponseDto());
    }
}

