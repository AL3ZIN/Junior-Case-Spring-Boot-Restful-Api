package com.itau.carros.adapters.out.port;

import com.itau.carros.adapters.out.persistence.mapper.CarroOutMapper;
import com.itau.carros.adapters.out.persistence.repository.CarroRepository;
import com.itau.carros.adapters.out.persistence.specification.CarroSpecification;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarroRepositoryPortImpl implements CarroRepositoryPort {

    private final CarroRepository carroRepository;
    private final CarroOutMapper mapper;
    private final CarroSpecification carroSpecification;

    public CarroRepositoryPortImpl(CarroRepository carroRepository, CarroOutMapper mapper, CarroSpecification carroSpecification) {
        this.carroRepository = carroRepository;
        this.mapper = mapper;
        this.carroSpecification = carroSpecification;
    }

    @Override
    public Carro save(Carro carro) {
        var entity = mapper.toEntity(carro);
        return mapper.toModel(carroRepository.save(entity));
    }

    @Override
    public List<Carro> findAllByAtivoTrue() {
        return carroRepository.findAllByAtivoTrue().stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Carro> findByIdAndAtivoTrue(Long id) {
        return carroRepository.findByIdAndAtivoTrue(id).map(mapper::toModel);
    }

    @Override
    public List<Carro> findAll(CriteriosDeBusca criteriosDeBusca) {
        return carroRepository.findAll(carroSpecification.comFiltro(criteriosDeBusca)).stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        carroRepository.findByIdAndAtivoTrue(id).orElseThrow().setAtivo(false);
    }

    @Override
    public boolean existsByChassiAndPlacaAndAtivoTrue(String chassi, String placa) {
        return carroRepository.existsByChassiAndPlacaAndAtivoTrue(chassi, placa);
    }
}
