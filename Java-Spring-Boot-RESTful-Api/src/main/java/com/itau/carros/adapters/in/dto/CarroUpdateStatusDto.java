package com.itau.carros.adapters.in.dto;

import com.itau.carros.application.core.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarroUpdateStatusDto {
    @NotNull
    Long id;

    @NotNull
    Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
