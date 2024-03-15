package com.itau.carros.adapters.out.persistence.specification;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.adapters.out.persistence.repository.CarroRepository;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CarroSpecificationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CarroRepository carroRepository;

    CarroSpecification carroSpecification;

    CarroEntity carroEntity;


    @BeforeEach
    void setUp() {
        carroSpecification = new CarroSpecification();
        carroEntity = new CarroEntity(
                "chassientity1",
                "Fusca",
                "Volkswagen",
                1970,
                "Azul",
                Status.ACTIVATED,
                "placaentity1"
        );
        entityManager.persistAndFlush(carroEntity); // Persiste e sincroniza com o banco de dados

    }

    @Test
    public void deveRetornarCarrosQuandoSomenteFabricanteEspecificado() {

        //ARRANGE
        var criteriosSomenteFabricante = new CriteriosDeBusca(null, "Volkswagen", null);

        //ACT
        var specSomenteFabricante = carroSpecification.criarSpecification(criteriosSomenteFabricante);
        var resultadoFabricante = carroRepository.findAll(specSomenteFabricante);

       //ASSERT
        assertThat(resultadoFabricante).isNotEmpty();
        assertThat(resultadoFabricante).allMatch(carro -> carro.getManufacturer().equals("Volkswagen"));
    }

    @Test
    public void deveRetornarCarrosQuandoSomenteAnoEspecificado() {

        //ARRANGE
        CriteriosDeBusca criteriosSomenteAno = new CriteriosDeBusca(null, null, 1970);

        //ACT
        Specification<CarroEntity> specSomenteAno = carroSpecification.criarSpecification(criteriosSomenteAno);
        List<CarroEntity> resultadoAno = carroRepository.findAll(specSomenteAno);

        //ASSERT
        assertThat(resultadoAno).isNotEmpty();
        assertThat(resultadoAno).allMatch(carro -> carro.getYear() == 1970);
    }

    @Test
    public void deveRetornarCarrosQuandoNomeEVazio() {

        //ARRANGE
        CriteriosDeBusca criteriosNomeVazio = new CriteriosDeBusca("", "Volkswagen", 1970);
        Specification<CarroEntity> specNomeVazio = carroSpecification.criarSpecification(criteriosNomeVazio);

        //ACT
        List<CarroEntity> resultadoNomeVazio = carroRepository.findAll(specNomeVazio);

        //ASSERT
        assertThat(resultadoNomeVazio).isNotEmpty();
        assertThat(resultadoNomeVazio).allMatch(carro -> carro.getManufacturer().equals("Volkswagen") && carro.getYear() == 1970);
    }

    @Test
    public void deveRetornarCarrosQuandoFabricanteEVazio() {

        //ARRANGE
        CriteriosDeBusca criteriosFabricanteVazio = new CriteriosDeBusca("Fusca", "", 1970);

        //ACT
        Specification<CarroEntity> specFabricanteVazio = carroSpecification.criarSpecification(criteriosFabricanteVazio);
        List<CarroEntity> resultadoFabricanteVazio = carroRepository.findAll(specFabricanteVazio);

        //ASSERT
        assertThat(resultadoFabricanteVazio).isNotEmpty();
        assertThat(resultadoFabricanteVazio).allMatch(carro -> carro.getName().equals("Fusca") && carro.getYear() == 1970);
    }
}