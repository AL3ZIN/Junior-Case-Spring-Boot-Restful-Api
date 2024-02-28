package com.itau.carros.adapters.out.persistence.specification;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroSpecification extends SpecificationBase<CarroEntity, CriteriosDeBusca> {

    @Override
    public Specification<CarroEntity> criarSpecification(CriteriosDeBusca criterios) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criterios.getName() != null && !criterios.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criterios.getName().toLowerCase() + "%"));
            }

            if (criterios.getManufacturer() != null && !criterios.getManufacturer().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("manufacturer"), criterios.getManufacturer()));
            }

            if (criterios.getYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), criterios.getYear()));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

}
