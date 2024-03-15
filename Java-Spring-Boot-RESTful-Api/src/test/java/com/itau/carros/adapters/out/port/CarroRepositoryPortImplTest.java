package com.itau.carros.adapters.out.port;

import com.itau.carros.adapters.out.persistence.entity.CarroEntity;
import com.itau.carros.adapters.out.persistence.mapper.CarroOutMapper;
import com.itau.carros.adapters.out.persistence.repository.CarroRepository;
import com.itau.carros.adapters.out.persistence.specification.CarroSpecification;
import com.itau.carros.application.core.enums.Status;
import com.itau.carros.application.core.model.Carro;
import com.itau.carros.application.core.vo.CriteriosDeBusca;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroRepositoryPortImplTest {

    @InjectMocks
    private CarroRepositoryPortImpl carroRepositoryPortImpl;

    @Mock
    CarroRepository carroRepository;

    @Mock
    private CarroOutMapper mapper;

    @Mock
    Specification<CarroEntity> specification;

    @Mock
    CarroSpecification carroSpecification;

    @Mock
    CriteriosDeBusca criteriosDeBusca;

    Carro carro;

    CarroEntity carroEntity;

    @BeforeEach
    void setUp() {
        carro = new Carro(
                "chassi1",
                "Fusca",
                "Volkswagen",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placa1"
        );

        carroEntity = new CarroEntity(
                "chassi1",
                "Fusca",
                "Volkswagen",
                2023,
                "Azul",
                Status.ACTIVATED,
                "placa1"
        );
    }

    @Test
    void DeveChamarRepositorioQuandoSalvarCarro() {

        //ARRANGE
        when(carroRepository.save(carroEntity)).thenReturn(carroEntity);
        when(mapper.toEntity(carro)).thenReturn(carroEntity);
        when(mapper.toModel(carroEntity)).thenReturn(carro);

        //ACT
        var response = carroRepositoryPortImpl.save(carro);

        //ASSERT
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(carro.getChassi(), response.getChassi()),
                () -> assertEquals(carro.getName(), response.getName()),
                () -> assertEquals(carro.getManufacturer(), response.getManufacturer()),
                () -> assertEquals(carro.getYear(), response.getYear()),
                () -> assertEquals(carro.getColor(), response.getColor()),
                () -> assertEquals(carro.getStatus(), response.getStatus()),
                () -> assertEquals(carro.getPlaca(), response.getPlaca())
        );

        verify(mapper, times(1)).toModel(carroEntity);
        verify(mapper, times(1)).toEntity(carro);
        verify(carroRepository, times(1)).save(carroEntity);
    }

    @Test
    void DeveChamarRepositorioQuandoListarCarros(){

        //ARRANGE
        when(carroRepository.findAllByAtivoTrue()).thenReturn(List.of(carroEntity));
        when(mapper.toModel(carroEntity)).thenReturn(carro);

        //ACT
        var response = carroRepositoryPortImpl.findAllByAtivoTrue();

        //ASSERT
        assertAll(
                () -> assertNotNull(response),
                ()-> assertEquals(response.size(), 1),
                () -> assertEquals(carroEntity.getChassi(), response.getFirst().getChassi()),
                () -> assertEquals(carroEntity.getName(), response.getFirst().getName()),
                () -> assertEquals(carroEntity.getManufacturer(), response.getFirst().getManufacturer()),
                () -> assertEquals(carroEntity.getYear(), response.getFirst().getYear()),
                () -> assertEquals(carroEntity.getColor(), response.getFirst().getColor()),
                () -> assertEquals(carroEntity.getStatus(), response.getFirst().getStatus()),
                () -> assertEquals(carroEntity.getPlaca(), response.getFirst().getPlaca())
        );

        verify(mapper, times(1)).toModel(carroEntity);
        verify(carroRepository, times(1)).findAllByAtivoTrue();
    }

    @Test
    void DeveChamarRepositorioQuandoListarCarroPorId(){

        //ARRANGE
        when(carroRepository.findByIdAndAtivoTrue(1L)).thenReturn(Optional.of(carroEntity));
        when(mapper.toModel(carroEntity)).thenReturn(carro);

        //ACT
        var response = carroRepositoryPortImpl.findByIdAndAtivoTrue(1L).get();

        //ASSERT
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(carroEntity.getChassi(), response.getChassi()),
                () -> assertEquals(carroEntity.getName(), response.getName()),
                () -> assertEquals(carroEntity.getManufacturer(), response.getManufacturer()),
                () -> assertEquals(carroEntity.getYear(), response.getYear()),
                () -> assertEquals(carroEntity.getColor(), response.getColor()),
                () -> assertEquals(carroEntity.getStatus(), response.getStatus()),
                () -> assertEquals(carroEntity.getPlaca(), response.getPlaca())
        );

        verify(mapper, times(1)).toModel(carroEntity);
        verify(carroRepository, times(1)).findByIdAndAtivoTrue(1L);
    }

    @Test
    void DeveChamarRepositorioQuandoListarCarrosSegundoFiltro(){

        //ARRANGE
        when(carroSpecification.comFiltro(criteriosDeBusca)).thenReturn(specification);
        when(carroRepository.findAll(specification)).thenReturn(List.of(carroEntity));
        when(mapper.toModel(carroEntity)).thenReturn(carro);

        //ACT
        var response = carroRepositoryPortImpl.findAll(criteriosDeBusca);

        //ASSERT
        assertAll(
                () -> assertNotNull(response),
                ()-> assertEquals(response.size(), 1),
                () -> assertEquals(carroEntity.getChassi(), response.getFirst().getChassi()),
                () -> assertEquals(carroEntity.getName(), response.getFirst().getName()),
                () -> assertEquals(carroEntity.getManufacturer(), response.getFirst().getManufacturer()),
                () -> assertEquals(carroEntity.getYear(), response.getFirst().getYear()),
                () -> assertEquals(carroEntity.getColor(), response.getFirst().getColor()),
                () -> assertEquals(carroEntity.getStatus(), response.getFirst().getStatus()),
                () -> assertEquals(carroEntity.getPlaca(), response.getFirst().getPlaca())
        );
        verify(carroSpecification, times(1)).comFiltro(criteriosDeBusca);
        verify(mapper, times(1)).toModel(carroEntity);
        verify(carroRepository, times(1)).findAll(specification);
    }

    @Test
    void DeveChamarRepositorioQuandoExcluirCarro(){

        //ARRANGE
        when(carroRepository.findByIdAndAtivoTrue(1L)).thenReturn(Optional.of(carroEntity));

        //ACT
        carroRepositoryPortImpl.delete(1L);

        //ASSERT
        assertFalse(carroEntity.isAtivo());
        verify(carroRepository, times(1)).findByIdAndAtivoTrue(1L);
    }

    @Test
    void DeveChamarRepositorioQuandoValidarCarroPorChassiPlacaEAtivo(){

        //ARRANGE
        when(carroRepository.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(),carro.getPlaca()))
                .thenReturn(true);

        //ACT + ASSERT
        assertTrue(carroRepositoryPortImpl.existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(),carro.getPlaca()));
        verify(carroRepository, times(1)).existsByChassiAndPlacaAndAtivoTrue(carro.getChassi(),carro.getPlaca());
    }
}