package com.itau.carros.adapters.out.persistence.repository;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long>, JpaSpecificationExecutor<CarroEntity>{
    List<CarroEntity> findAllByAtivoTrue();
    Page<CarroEntity> findByAtivoTrue(Specification<CarroEntity> carroEntitySpecification, Pageable pageable);
    Optional<CarroEntity> findByIdAndAtivoTrue(Long id);

    boolean existsByChassiOrPlacaAndAtivoIsTrue(String chassi, String placa);
}
