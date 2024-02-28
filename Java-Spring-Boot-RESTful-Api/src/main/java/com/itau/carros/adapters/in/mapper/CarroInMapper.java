package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.CarroRequestDto;
import com.itau.carros.adapters.in.dto.CarroFiltroRequestDto;
import com.itau.carros.adapters.in.dto.CarroListagemAgrupadaResponseDto;
import com.itau.carros.adapters.in.dto.CarroListagemResponseDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CarroInMapper {

    public Carro toModel(CarroRequestDto dto){
        var model = new Carro();
        copyProperties(dto,model);
        return model;
    }

    public CarroListagemResponseDto toDto(Carro model) {
        var dto = new CarroListagemResponseDto();
        copyProperties(model, dto);
        return dto;
    }


    public CarroListagemAgrupadaResponseDto toDto(String manufacturer, List<EntityModel<CarroListagemResponseDto>> carros){
        var dto = new CarroListagemAgrupadaResponseDto();
        dto.setManufacturer(manufacturer);
        dto.setCarros(carros);
        return dto;
    }

    public CriteriosDeBusca toModel(CarroFiltroRequestDto dto) {
        var model = new CriteriosDeBusca();
        copyProperties(dto, model);
        System.out.println(model);
        return model;
    }
}
