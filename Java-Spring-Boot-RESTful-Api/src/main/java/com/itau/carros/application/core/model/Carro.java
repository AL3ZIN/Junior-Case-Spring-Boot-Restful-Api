package com.itau.carros.application.core.model;

import com.itau.carros.application.core.enums.Status;

public class Carro {

    private Long id;
    private String chassi;
    private String name;
    private String manufacturer;
    private Integer year;
    private String color;
    private Status status;
    private String placa;

    public Carro() {
    }
    public Carro( String chassi, String name, String manufacturer, Integer year, String color, Status status, String placa) {
        this.chassi = chassi;
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
        this.color = color;
        this.status = status;
        this.placa = placa;
    }
    public Carro(Long id, String chassi, String name, String manufacturer, Integer year, String color, Status status, String placa) {
        this.id = id;
        this.chassi = chassi;
        this.name = name;
        this.manufacturer = manufacturer;
        this.year = year;
        this.color = color;
        this.status = status;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
