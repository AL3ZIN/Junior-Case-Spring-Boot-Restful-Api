package com.itau.carros.adapters.out.persistence.specification;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import static org.mockito.Mockito.*;

public class SpecificationBaseTest {
    @Mock
    private Root<CarroEntity> root;

    @Mock
    private CriteriaQuery<?> query;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Predicate predicateAtivo, predicateEspecifico;

    @InjectMocks
    private CarroSpecification specification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(criteriaBuilder.isTrue(any())).thenReturn(predicateAtivo);
    }

    @Test
    void testComFiltroAplicaCondicaoAtivo() {
        CriteriosDeBusca criterios = new CriteriosDeBusca();

        when(criteriaBuilder.and(predicateAtivo, predicateEspecifico)).thenReturn(predicateEspecifico);

        Specification<CarroEntity> generatedSpecification = specification.comFiltro(criterios);

        generatedSpecification.toPredicate(root, query, criteriaBuilder);

        verify(criteriaBuilder).isTrue(root.get("ativo"));
    }
}