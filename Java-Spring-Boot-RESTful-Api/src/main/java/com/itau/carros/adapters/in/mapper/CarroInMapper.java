package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.carro.CarroFiltroRequestDto;
import com.itau.carros.adapters.in.dto.carro.CarroListagemAgrupadaResponseDto;
import com.itau.carros.adapters.in.dto.carro.CarroListagemResponseDto;
import com.itau.carros.adapters.in.dto.carro.CarroRequestDto;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarroInMapper {

    public Carro toModel(CarroRequestDto dto){
       return new Carro(
                dto.getChassi(),
                dto.getName(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getColor(),
                dto.getStatus(),
                dto.getPlaca()
        );
    }

    public CarroListagemResponseDto toDto(Carro model) {
        return new CarroListagemResponseDto(
                model.getId(),
                model.getChassi(),
                model.getName(),
                model.getManufacturer(),
                model.getYear(),
                model.getColor(),
                model.getStatus(),
                model.getPlaca()
        );
    }


    public CarroListagemAgrupadaResponseDto toDto(String manufacturer, List<EntityModel<CarroListagemResponseDto>> carros){
        return new CarroListagemAgrupadaResponseDto(
                manufacturer,
                carros
        );
    }

    public CriteriosDeBusca toModel(CarroFiltroRequestDto dto) {
        return new CriteriosDeBusca(
                dto.getName(),
                dto.getManufacturer(),
                dto.getYear()
        );
    }
}
