package com.itau.carros.adapters.in.hateoas;

import com.itau.carros.adapters.in.controller.CarroController;
import com.itau.carros.adapters.in.dto.carro.CarroListagemResponseDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarroListagemDtoAssembler implements RepresentationModelAssembler<CarroListagemResponseDto, EntityModel<CarroListagemResponseDto>> {

    @Override
    public EntityModel<CarroListagemResponseDto> toModel(CarroListagemResponseDto dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(CarroController.class).detalhar(dto.getId())).withSelfRel(),
                linkTo(methodOn(CarroController.class).listar()).withRel("carros"));
    }
}


