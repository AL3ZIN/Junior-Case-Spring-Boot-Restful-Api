package com.itau.carros.adapters.in.handler;

import com.itau.carros.adapters.in.response.ExceptionResponse;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.usecase.GetCarroUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;



import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
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

    @MockBean
    ExceptionResponse exceptionResponse;



    // Teste para tratarErro404
    @Test
    void tratarErro400Test() throws Exception {
        when(getCarroUseCase.listar()).thenThrow(new ConsultaNulaException("Mensagem"));

        mockMvc.perform(get("/api/carro"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Mensagem"));
    }

}
