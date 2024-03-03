package com.itau.carros.adapters.in.handler;

import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.usecase.carro.GetCarroUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TratadorDeErrosTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCarroUseCaseImpl getCarroUseCase;



    @Test
    @WithMockUser
    void tratarErro404Test() throws Exception {
        when(getCarroUseCase.listar()).thenThrow(new ConsultaNulaException("Mensagem"));

        mockMvc.perform(get("/api/carro"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Mensagem"));
    }

}
