package com.itau.carros.adapters.in.manager;

import com.itau.carros.adapters.in.dto.CarroDto;
import com.itau.carros.adapters.in.dto.CarroFiltroDto;
import com.itau.carros.adapters.in.dto.CarroListagemDto;
import com.itau.carros.adapters.in.dto.CarroUpdateStatusDto;
import com.itau.carros.adapters.in.mapper.CarroInMapper;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.in.CreateCarroUseCasePort;
import com.itau.carros.application.ports.in.DeleteCarroUseCasePort;
import com.itau.carros.application.ports.in.GetCarroUseCasePort;
import com.itau.carros.application.ports.in.UpdateCarroUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Long cadastrar(CarroDto dto){
        return createCarroUseCasePort.cadastrar(CarroInMapper.toModel(dto));
    }

    public List<CarroListagemDto> listar(){
        Map<String, List<CarroDto>> groupedByManufacturer = getCarroUseCasePort.listar().stream()
                .map(CarroInMapper::toDto)
                .collect(Collectors.groupingBy(CarroDto::getManufacturer)) ;

        return groupedByManufacturer.entrySet().stream()
                .map(entry -> {
                    List<CarroDto> sortedCars = entry.getValue().stream()
                            .sorted(Comparator.comparing(CarroDto::getName)
                                    .thenComparing(CarroDto::getYear, Comparator.reverseOrder()))
                            .collect(Collectors.toList());
                    return CarroInMapper.toDto(entry.getKey(), sortedCars);
                })
                .sorted(Comparator.comparing(CarroListagemDto::getManufacturer))
                .collect(Collectors.toList());

    }

    public CarroDto detalhar(Long id){
        return CarroInMapper.toDto(getCarroUseCasePort.detalhar(id));
    }

    public List<CarroDto> filtrar(CarroFiltroDto dto){
        return getCarroUseCasePort.filtrar(CarroInMapper.toModel(dto)).stream()
                .map(CarroInMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarroDto atualizarStatus(CarroUpdateStatusDto dto){
        return CarroInMapper.toDto(updateCarroUseCasePort.atualizarStatus(dto.getId(),dto.getStatus()));
    }

    public void excluir(Long id){
        deleteCarroUseCasePort.excluir(id);
    }
}
