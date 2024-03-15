package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.carro.*;
import com.itau.carros.adapters.in.service.CarroService;
import com.itau.carros.application.core.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService carroService;

    CarroRequestDto carroRequestDto;

    @MockBean
    CarroListagemResponseDto carroListagemResponseDto;

    @MockBean
    CarroListagemAgrupadaResponseDto carroListagemAgrupadaResponseDto;

    @MockBean
    CarroFiltroRequestDto carroFiltroRequestDto;

    @MockBean
    CarroUpdateStatusRequestDto carroUpdateStatusRequestDto;

    @Autowired
    JacksonTester<CarroRequestDto> carroRequestDtoJson;
    @Autowired
    JacksonTester<CarroUpdateStatusRequestDto> carroUpdateStatusRequestDtoJson;

    @BeforeEach
    void setUp() {
        carroRequestDto = new CarroRequestDto(
                "chassiDto1",
                "Camaro",
                "Chevrolet",
                2021,
                "Amarelo",
                Status.RENTED,
                "placadto1"
        );

        carroUpdateStatusRequestDto = new CarroUpdateStatusRequestDto(1L, Status.ACTIVATED);
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo201ParaRequisicaoDeCadastrarCarro() throws Exception {

        // ARRANGE
        when(carroService.cadastrar(carroRequestDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/carro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carroRequestDtoJson.write(carroRequestDto).getJson())
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaRequisicaoDeListarCarros() throws Exception {

        // ARRANGE
        when(carroService.listar()).thenReturn(List.of(carroListagemAgrupadaResponseDto));

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/carro")
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaRequisicaoDeDetalharCarro() throws Exception {

        // ARRANGE
        Long id = 1L;
        when(carroService.detalhar(id)).thenReturn(Optional.of(EntityModel.of(carroListagemResponseDto)));

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/carro/{id}", id)
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo204ParaRequisicaoDeFiltrarCarro() throws Exception {

        // ARRANGE
        when(carroService.filtrar(carroFiltroRequestDto)).thenReturn(List.of(EntityModel.of(carroListagemResponseDto)));

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/carro/filtrar")
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaRequisicaoDeAtualizarStatusDeUmCarro() throws Exception {

        //ASSERT
        when(carroService.atualizarStatus(carroUpdateStatusRequestDto)).thenReturn(EntityModel.of(carroListagemResponseDto));

        // ACT + ASSERT
        mockMvc.perform(MockMvcRequestBuilders.put("/api/carro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(carroUpdateStatusRequestDtoJson.write(carroUpdateStatusRequestDto).getJson())
        ).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo204ParaRequisicaoDeExcluirCarro() throws Exception {

        // ARRANGE
        Long id = 1L;

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/carro/{id}", id)
        ).andExpect(status().isNoContent());

    }

}