package com.itau.carros.adapters.in.dto.carro;

public class CarroFiltroRequestDto {
    private String name;
    private String manufacturer;
    private Integer year;

    public CarroFiltroRequestDto(){}

    public CarroFiltroRequestDto(String name, String manufacturer, Integer year) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
