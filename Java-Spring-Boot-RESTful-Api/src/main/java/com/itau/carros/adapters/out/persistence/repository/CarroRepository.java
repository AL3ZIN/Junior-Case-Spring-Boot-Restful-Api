package com.itau.carros.adapters.out.persistence.repository;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Long>, JpaSpecificationExecutor<CarroEntity>{
    List<CarroEntity> findAllByAtivoTrue();
    Optional<CarroEntity> findByIdAndAtivoTrue(Long id);
    boolean existsByChassiAndPlacaAndAtivoTrue(String chassi, String placa);
}
