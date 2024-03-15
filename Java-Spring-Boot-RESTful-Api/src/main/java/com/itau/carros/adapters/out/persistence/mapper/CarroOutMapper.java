package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.model.Carro;
import org.springframework.stereotype.Component;

@Component
public class CarroOutMapper {

    public CarroEntity toEntity(Carro model) {
        return new CarroEntity(
                model.getChassi(),
                model.getName(),
                model.getManufacturer(),
                model.getYear(),
                model.getColor(),
                model.getStatus(),
                model.getPlaca()
        );
    }

    public Carro toModel(CarroEntity entity) {
        return new Carro(
                entity.getId(),
                entity.getChassi(),
                entity.getName(),
                entity.getManufacturer(),
                entity.getYear(),
                entity.getColor(),
                entity.getStatus(),
                entity.getPlaca()
        );
    }

}
