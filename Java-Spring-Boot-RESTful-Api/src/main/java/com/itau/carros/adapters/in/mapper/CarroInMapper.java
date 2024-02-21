package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.controller.CarroController;
import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.adapters.in.dto.CarroFiltroDto;
import com.itau.carros.adapters.in.dto.CarroListagemAgrupadaDto;
import com.itau.carros.adapters.in.dto.CarroListagemDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CarroInMapper {

    public static Carro toModel(CarroDto dto){
        var model = new Carro();
        copyProperties(dto,model);
        return model;
    }

    public static CarroListagemDto toDto(Carro model) {
        var dto = new CarroListagemDto();
        var link = linkTo(methodOn(CarroController.class).detalhar(model.getId())).withSelfRel();
        copyProperties(model, dto);
        return dto.add(link);
    }


    public static CarroListagemAgrupadaDto toDto(String manufacturer, List<CarroListagemDto> carros){
        var dto = new CarroListagemAgrupadaDto();
        carros.forEach(c ->{
            Link link = linkTo(methodOn(CarroController.class).detalhar(c.getId())).withSelfRel();
        });

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
