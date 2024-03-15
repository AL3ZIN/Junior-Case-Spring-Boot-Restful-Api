package com.itau.carros.adapters.out.persistence.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;


public abstract class SpecificationBase<E, C> {

    protected abstract Specification<E> criarSpecification(C criterios);


    public Specification<E> comFiltro(C criterios) {
        return (root, query, criteriaBuilder) -> {
            Predicate condicaoAtivo = criteriaBuilder.isTrue(root.get("ativo"));
            Predicate condicoesDaSubclasse = criarSpecification(criterios).toPredicate(root, query, criteriaBuilder);
            return criteriaBuilder.and(condicaoAtivo, condicoesDaSubclasse);
        };
    }
}

