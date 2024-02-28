package com.itau.carros.mock;

import com.itau.carros.adapters.in.dto.*;
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

    public CarroRequestDto getCarroDto(){
        CarroRequestDto carroDto = new CarroRequestDto();
        carroDto.setChassi("chassiDto1");
        carroDto.setStatus(Status.RENTED);
        carroDto.setName("Camaro");
        carroDto.setYear(2021);
        carroDto.setManufacturer("Chevrolet");
        carroDto.setColor("Amarelo");
        carroDto.setPlaca("placadto1");
        return carroDto;
    }

    public List<CarroRequestDto> getCarroDtoList(){
        return Collections.singletonList(getCarroDto());
    }

    public CarroListagemAgrupadaResponseDto getCarroListagemAgrupadaDto(){
        CarroListagemAgrupadaResponseDto carroListagemDto = new CarroListagemAgrupadaResponseDto();
        carroListagemDto.setCarros(getCarroListagemDtoList());
        carroListagemDto.setManufacturer(getCarroDto().getManufacturer());
        return carroListagemDto;
    }
    public List<CarroListagemAgrupadaResponseDto> getCarroListagemAgrupadaDtoList(){
        return Collections.singletonList(getCarroListagemAgrupadaDto());
    }

    public CarroFiltroRequestDto getCarroFiltroDto(){
        CarroFiltroRequestDto carroFiltroDto = new CarroFiltroRequestDto();
        carroFiltroDto.setName("Fusca");
        carroFiltroDto.setManufacturer("Lamborghini");
        carroFiltroDto.setYear(2024);
        return carroFiltroDto;
    }

    public CarroUpdateStatusRequestDto getCarroUpdateStatusDto() {
        var carroUpdateStatusDto = new CarroUpdateStatusRequestDto();
        carroUpdateStatusDto.setStatus(Status.DEACTIVATED);
        carroUpdateStatusDto.setId(1L);
        return carroUpdateStatusDto;
    }

    public EntityModel<CarroListagemResponseDto> getCarroListagemDto(){
        var carroListagemDto = new CarroListagemResponseDto();
        carroListagemDto.setId(1L);
        carroListagemDto.setChassi("chassiDto1");
        carroListagemDto.setStatus(Status.RENTED);
        carroListagemDto.setName("Camaro");
        carroListagemDto.setYear(2021);
        carroListagemDto.setManufacturer("Chevrolet");
        carroListagemDto.setColor("Amarelo");
        carroListagemDto.setPlaca("placadto1");
        return EntityModel.of(carroListagemDto);
    }

    public List<EntityModel<CarroListagemResponseDto>> getCarroListagemDtoList() {
        return Collections.singletonList(getCarroListagemDto());
    }

    public Optional<EntityModel<CarroListagemResponseDto>> getOptionalEntityModelCarroListagemDto(){
        return Optional.of(getCarroListagemDto());
    }
}

