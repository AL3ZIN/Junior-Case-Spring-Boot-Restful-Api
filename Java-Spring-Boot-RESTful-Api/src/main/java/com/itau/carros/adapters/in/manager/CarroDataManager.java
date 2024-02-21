package com.itau.carros.adapters.in.manager;

import com.itau.carros.adapters.in.controller.CarroController;
import com.itau.carros.adapters.in.dto.*;
import com.itau.carros.adapters.in.mapper.CarroInMapper;
import com.itau.carros.application.ports.in.CreateCarroUseCasePort;
import com.itau.carros.application.ports.in.DeleteCarroUseCasePort;
import com.itau.carros.application.ports.in.GetCarroUseCasePort;
import com.itau.carros.application.ports.in.UpdateCarroUseCasePort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarroDataManager {

    private final CreateCarroUseCasePort createCarroUseCasePort;
    private final GetCarroUseCasePort getCarroUseCasePort;
    private final UpdateCarroUseCasePort updateCarroUseCasePort;
    private final DeleteCarroUseCasePort deleteCarroUseCasePort;

    public CarroDataManager(CreateCarroUseCasePort createCarroUseCasePort, GetCarroUseCasePort getCarroUseCasePort, UpdateCarroUseCasePort updateCarroUseCasePort, DeleteCarroUseCasePort deleteCarroUseCasePort) {
        this.createCarroUseCasePort = createCarroUseCasePort;
        this.getCarroUseCasePort = getCarroUseCasePort;
        this.updateCarroUseCasePort = updateCarroUseCasePort;
        this.deleteCarroUseCasePort = deleteCarroUseCasePort;
    }

    public CarroListagemDto cadastrar(CarroDto dto){
        var entitySaved = createCarroUseCasePort.cadastrar(CarroInMapper.toModel(dto));
        return  CarroInMapper.toDto(entitySaved) ;
    }

    public List<CarroListagemAgrupadaDto> listar(){

        Map<String, List<CarroListagemDto>> groupedByManufacturer = getCarroUseCasePort.listar().stream()
                .map(CarroInMapper::toDto)
                .collect(Collectors.groupingBy(CarroListagemDto::getManufacturer)) ;

        Link link = linkTo(methodOn(CarroController.class)).slash(1).withSelfRel();
        return groupedByManufacturer.entrySet().stream()
                .map(entry -> {
                    List<CarroListagemDto> sortedCarros = entry.getValue().stream()
                            .sorted(Comparator.comparing(CarroListagemDto::getName)
                                    .thenComparing(CarroListagemDto::getYear, Comparator.reverseOrder()))
                            .collect(Collectors.toList());
                    return CarroInMapper.toDto(entry.getKey(), sortedCarros);
                })
                .sorted(Comparator.comparing(CarroListagemAgrupadaDto::getManufacturer))
                .collect(Collectors.toList());

    }

    public Optional<EntityModel<CarroListagemDto>> detalhar(Long id){
        return Optional.ofNullable(getCarroUseCasePort.detalhar(id))
                .map(carro -> {
                    CarroListagemDto carroDto = CarroInMapper.toDto(carro);
                    Link link = linkTo(methodOn(CarroController.class).detalhar(id)).withSelfRel();
                    return EntityModel.of(carroDto, link);
                });
    }

    public List<CarroListagemDto> filtrar(CarroFiltroDto dto){
        return getCarroUseCasePort.filtrar(CarroInMapper.toModel(dto)).stream()
                .map(CarroInMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarroListagemDto atualizarStatus(CarroUpdateStatusDto dto){
        return CarroInMapper.toDto(updateCarroUseCasePort.atualizarStatus(dto.getId(),dto.getStatus()));
    }

    public void excluir(Long id){
        deleteCarroUseCasePort.excluir(id);
    }
}
