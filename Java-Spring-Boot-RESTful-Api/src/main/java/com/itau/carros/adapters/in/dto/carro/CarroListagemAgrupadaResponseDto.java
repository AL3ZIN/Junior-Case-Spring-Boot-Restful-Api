package com.itau.carros.adapters.in.dto.carro;

import org.springframework.hateoas.EntityModel;

import java.util.List;

public class CarroListagemAgrupadaResponseDto {
    private String manufacturer;
    private List<EntityModel<CarroListagemResponseDto>> carros;

    public CarroListagemAgrupadaResponseDto(){}

    public CarroListagemAgrupadaResponseDto(String manufacturer, List<EntityModel<CarroListagemResponseDto>> carros) {
        this.manufacturer = manufacturer;
        this.carros = carros;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<EntityModel<CarroListagemResponseDto>> getCarros() {
        return carros;
    }

    public void setCarros(List<EntityModel<CarroListagemResponseDto>> carros) {
        this.carros = carros;
    }
}
