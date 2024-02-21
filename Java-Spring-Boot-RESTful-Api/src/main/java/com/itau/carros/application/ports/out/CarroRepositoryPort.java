package com.itau.carros.application.ports.out;

import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CarroRepositoryPort {
    Carro save(Carro carro);

    List<Carro> findAllByAtivoTrue();

    Optional<Carro> findByIdAndAtivoTrue(Long id);

    List<Carro> findAll(CriteriosDeBusca criteriosDeBusca);

    void delete(Long id);

    boolean existsByChassiAndPlacaAndAtivoTrue(String chassi, String placa);

}
