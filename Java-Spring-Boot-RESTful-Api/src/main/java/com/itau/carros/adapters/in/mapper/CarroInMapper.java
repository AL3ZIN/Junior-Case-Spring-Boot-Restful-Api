package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.adapters.in.dto.CarroFiltroDto;
import com.itau.carros.adapters.in.dto.CarroListagemDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

public class CarroInMapper {

    public static Carro toModel(CarroDto dto){
        var model = new Carro();
        copyProperties(dto,model);
        return model;
    }

    public static CarroDto toDto(Carro model) {
        var dto = new CarroDto();
        copyProperties(model, dto);
        return dto;
    }

    public static CarroListagemDto toDto(String manufacturer, List<CarroDto> carros){
        var dto = new CarroListagemDto();
        dto.setManufacturer(manufacturer);
        dto.setCarros(carros);
        return dto;
    }


    public static CriteriosDeBusca toModel(CarroFiltroDto dto) {
        var model = new CriteriosDeBusca();
        copyProperties(dto, model);
        System.out.println(model);
        return model;
    }
}
