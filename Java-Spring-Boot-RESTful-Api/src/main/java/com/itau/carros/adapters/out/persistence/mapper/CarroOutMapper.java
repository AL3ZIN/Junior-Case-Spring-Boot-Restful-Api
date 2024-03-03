package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.model.Carro;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class CarroOutMapper {

    public CarroEntity toEntity(Carro model){
        var entity = new CarroEntity();
        copyProperties(model,entity);
        return entity;
    }

    public Carro toModel(CarroEntity entity){
        var carro = new Carro();
        copyProperties(entity, carro);
        return carro;
    }

}
