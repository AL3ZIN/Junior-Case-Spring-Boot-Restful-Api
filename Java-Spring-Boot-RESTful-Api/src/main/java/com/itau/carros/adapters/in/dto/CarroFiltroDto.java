package com.itau.carros.adapters.in.dto;

public class CarroFiltroDto {
    private String name;
    private String manufacturer;
    private Integer year;

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

    @Override
    public String toString() {
        return "CarroFiltroDto{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                '}';
    }
}
