package com.itau.carros.adapters.in.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.carros.adapters.in.service.CarroService;
import com.itau.carros.mock.MockSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarroService dataManager;


    MockSingleton mockSingleton = MockSingleton.getInstance();

    @Test
    @WithMockUser
    void deveriaDevolverCodigo201ParaRequisicaoDeCadastrarCarro() throws Exception {

        // ARRANGE
        String json = new ObjectMapper().writeValueAsString(mockSingleton.getCarroRequestDto());
        when(dataManager.cadastrar(mockSingleton.getCarroRequestDto())).thenReturn(mockSingleton.getEntityModelCarroListagemResponseDto());

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/carro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaRequisicaoDeListarCarros() throws Exception {

        // ARRANGE
        when(dataManager.listar()).thenReturn(mockSingleton.getCarroListagemAgrupadaResponseDtoList());

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
        when(dataManager.detalhar(id)).thenReturn(mockSingleton.getOptionalEntityModelCarroListagemResponseDto());

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/carro/{id}", id)
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void deveriaDevolverCodigo204ParaRequisicaoDeFiltrarCarro() throws Exception {

        // ARRANGE
        when(dataManager.filtrar(mockSingleton.getCarroFiltroRequestDto())).thenReturn(mockSingleton.getEntityModelCarroListagemResponseDtoList());

        // ACT + ASSERT
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/carro/filtrar")
        ).andExpect(status().isOk());
    }
    @Test
    @WithMockUser
    void deveriaDevolverCodigo200ParaRequisicaoDeAtualizarStatusDeUmCarro() throws Exception {

        // ARRANGE
        Long id = 1L;
        String json = new ObjectMapper().writeValueAsString(mockSingleton.getCarroUpdateStatusRequestDto());

        // ACT + ASSERT
        mockMvc.perform(MockMvcRequestBuilders.put("/api/carro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
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