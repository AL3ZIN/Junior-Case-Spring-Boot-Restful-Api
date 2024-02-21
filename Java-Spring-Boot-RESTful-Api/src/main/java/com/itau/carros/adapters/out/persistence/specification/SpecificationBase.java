package com.itau.carros.adapters.out.persistence.specification;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public abstract class SpecificationBase<E, C> {

    // Método protegido para ser implementado pelas subclasses, criando Specifications sem considerar 'ativo'
    protected abstract Specification<E> criarSpecification(C criterios);


    public Specification<E> comFiltro(C criterios) {
        return (root, query, criteriaBuilder) -> {
            Predicate condicaoAtivo = criteriaBuilder.isTrue(root.get("ativo"));
            Predicate condicoesDaSubclasse = criarSpecification(criterios).toPredicate(root, query, criteriaBuilder);
            return criteriaBuilder.and(condicaoAtivo, condicoesDaSubclasse);
        };
    }
}

