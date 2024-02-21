package com.itau.carros.adapters.in.dto;

import java.util.List;

public class CarroListagemDto {
    private String manufacturer;
    private List<CarroDto> carros;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<CarroDto> getCarros() {
        return carros;
    }

    public void setCarros(List<CarroDto> carros) {
        this.carros = carros;
    }
}
