package com.itau.carros.adapters.out.port;

import com.itau.carros.adapters.out.persistence.mapper.CarroOutMapper;
import com.itau.carros.adapters.out.persistence.repository.CarroRepository;
import com.itau.carros.adapters.out.persistence.specification.CarroSpecification;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import com.itau.carros.application.ports.out.CarroRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CarroRepositoryPortImpl implements CarroRepositoryPort {

    private final CarroRepository carroRepository;
    private final CarroSpecification carroSpecification;

    public CarroRepositoryPortImpl(CarroRepository carroRepository, CarroSpecification carroSpecification) {
        this.carroRepository = carroRepository;
        this.carroSpecification = carroSpecification;
    }

    @Override
    public Long save(Carro carro) {
        var entity = CarroOutMapper.toEntity(carro);
        carroRepository.save(entity);
        return entity.getId();
    }

    @Override
    public List<Carro> findAllByAtivoTrue() {
        return carroRepository.findAllByAtivoTrue().stream()
                .map(CarroOutMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Carro> findByIdAndAtivoTrue(Long id) {
        return carroRepository.findByIdAndAtivoTrue(id).map(CarroOutMapper::toModel);
    }

    @Override
    public List<Carro> findAll(CriteriosDeBusca criteriosDeBusca) {
        return carroRepository.findAll(carroSpecification.comFiltro(criteriosDeBusca)).stream()
                .map(CarroOutMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        carroRepository.findByIdAndAtivoTrue(id).orElseThrow().setAtivo(false);
    }

    @Override
    public boolean existsByChassiOrPlacaAndAtivoIsTrue(String chassi, String placa) {
        return carroRepository.existsByChassiOrPlacaAndAtivoIsTrue(chassi, placa);
    }
}
