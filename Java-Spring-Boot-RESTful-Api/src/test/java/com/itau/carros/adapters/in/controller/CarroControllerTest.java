//package com.itau.carros.adapters.in.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.itau.carros.adapters.in.dto.CarroDto;
//import com.itau.carros.adapters.in.manager.CarroDataManager;
//import com.itau.carros.mock.MockSingleton;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CarroControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CarroDataManager dataManager;
//
//
//    MockSingleton mockSingleton = MockSingleton.getInstance();
//
//    @Test
//    void deveriaDevolverCodigo201ParaRequisicaoDeCadastrarCarro() throws Exception {
//        // Arrange
//        CarroDto carroDto = mockSingleton.getCarroDto();
//        String json = new ObjectMapper().writeValueAsString(carroDto);
//        when(dataManager.cadastrar(carroDto)).thenReturn(1L);
//
//        // Act
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/carro")
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isCreated());
//
//        // Assert
//
//    }
//
//}