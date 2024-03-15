package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.dto.carro.*;
import com.itau.carros.adapters.in.hateoas.CarroListagemDtoAssembler;
import com.itau.carros.adapters.in.mapper.CarroInMapper;
import com.itau.carros.application.ports.in.carro.CreateCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.DeleteCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.GetCarroUseCasePort;
import com.itau.carros.application.ports.in.carro.UpdateCarroUseCasePort;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarroService {

    private final CreateCarroUseCasePort createCarroUseCasePort;
    private final GetCarroUseCasePort getCarroUseCasePort;
    private final UpdateCarroUseCasePort updateCarroUseCasePort;
    private final DeleteCarroUseCasePort deleteCarroUseCasePort;
    private final CarroInMapper mapper;
    private final CarroListagemDtoAssembler assembler;

    public CarroService(CreateCarroUseCasePort createCarroUseCasePort, GetCarroUseCasePort getCarroUseCasePort, UpdateCarroUseCasePort updateCarroUseCasePort, DeleteCarroUseCasePort deleteCarroUseCasePort, CarroInMapper mapper, CarroListagemDtoAssembler carroListagemDtoAssembler) {
        this.createCarroUseCasePort = createCarroUseCasePort;
        this.getCarroUseCasePort = getCarroUseCasePort;
        this.updateCarroUseCasePort = updateCarroUseCasePort;
        this.deleteCarroUseCasePort = deleteCarroUseCasePort;
        this.mapper = mapper;
        this.assembler = carroListagemDtoAssembler;
    }

    public EntityModel<CarroListagemResponseDto> cadastrar(CarroRequestDto dto){
        var entitySaved = createCarroUseCasePort.cadastrar(mapper.toModel(dto));
        return  assembler.toModel(mapper.toDto(entitySaved)) ;
    }

    public List<CarroListagemAgrupadaResponseDto> listar(){

        Map<String, List<EntityModel<CarroListagemResponseDto>>> groupedByManufacturer = getCarroUseCasePort.listar().stream()
                .map(mapper::toDto)
                .map(assembler::toModel)
                .collect(Collectors.groupingBy(dto -> dto.getContent().getManufacturer())) ;

        return groupedByManufacturer.entrySet().stream()
                .map(entry -> criarCarroListagemAgrupadaDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CarroListagemAgrupadaResponseDto::getManufacturer))
                .collect(Collectors.toList());
    }

    public Optional<EntityModel<CarroListagemResponseDto>> detalhar(Long id){
        return Optional.of(getCarroUseCasePort.detalhar(id))
                .map(mapper::toDto)
                .map(assembler::toModel);
    }

    public List<EntityModel<CarroListagemResponseDto>> filtrar(CarroFiltroRequestDto dto){
        return getCarroUseCasePort.filtrar(mapper.toModel(dto)).stream()
                .map(mapper::toDto)
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }

    public EntityModel<CarroListagemResponseDto> atualizarStatus(CarroUpdateStatusRequestDto dto){
        return assembler.toModel(mapper.toDto(updateCarroUseCasePort.atualizarStatus(dto.getId(),dto.getStatus())));
    }

    public void excluir(Long id){
        deleteCarroUseCasePort.excluir(id);
    }


    public CarroListagemAgrupadaResponseDto criarCarroListagemAgrupadaDto(String manufacturer, List<EntityModel<CarroListagemResponseDto>> carrosModel) {
        // Ordena os carros por nome e depois por ano em ordem decrescente, mantendo a estrutura EntityModel
        List<EntityModel<CarroListagemResponseDto>> sortedCarros = carrosModel.stream()
                .sorted(Comparator.comparing((EntityModel<CarroListagemResponseDto> em) -> em.getContent().getName())
                        .thenComparing(em -> em.getContent().getYear(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return mapper.toDto(manufacturer, sortedCarros);
    }

}


