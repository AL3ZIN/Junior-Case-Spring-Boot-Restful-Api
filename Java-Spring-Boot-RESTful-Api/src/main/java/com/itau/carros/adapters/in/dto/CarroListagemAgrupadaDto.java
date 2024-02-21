package com.itau.carros.adapters.in.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class CarroListagemAgrupadaDto {
    private String manufacturer;
    private List<CarroListagemDto> carros;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<CarroListagemDto> getCarros() {
        return carros;
    }

    public void setCarros(List<CarroListagemDto> carros) {
        this.carros = carros;
    }
}
