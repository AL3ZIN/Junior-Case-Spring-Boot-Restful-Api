package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

public class CarroOutMapper {

    public static CarroEntity toEntity(Carro model){
        var entity = new CarroEntity();
        copyProperties(model,entity);
        return entity;
    }

    public static Carro toModel(CarroEntity entity){
        var carro = new Carro();
        copyProperties(entity, carro);
        return carro;
    }

}
