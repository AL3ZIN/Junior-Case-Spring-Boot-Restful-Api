package com.itau.carros.adapters.in.handler;

import com.itau.carros.adapters.in.response.ExceptionResponse;
import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.exception.UnicidadeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TratadorDeErrosTest {

    @InjectMocks
    private TratadorDeErros tratadorDeErros;

    @Mock
    private WebRequest request;

    @Mock
    ConsultaNulaException consultaNulaException;

    @Mock
    MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    BadCredentialsException badCredentialsException;

    @Mock
    UnicidadeException unicidadeException;

    @Mock
    Exception exception;


    @BeforeEach
    void setUp() {
        when(request.getDescription(false)).thenReturn("detalhe da requisicao");
    }


    @Test
    void deveRetornarErro400QuandoMethodArgumentNotValidException() {

        //ACT
        ResponseEntity<ExceptionResponse> resposta = tratadorDeErros.tratarErro400(methodArgumentNotValidException, request);

        //ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    void deveRetornarErro403QuandoBadCredentialsException() {

        //ACT
        ResponseEntity<ExceptionResponse> resposta = tratadorDeErros.tratarErro403(badCredentialsException, request);

        //ASSERT
        assertEquals(HttpStatus.FORBIDDEN, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    void deveRetornarErro404QuandoEntityNotFoundException() {

        //ACT
        ResponseEntity<ExceptionResponse> resposta = tratadorDeErros.tratarErro404(consultaNulaException, request);

        //ASSERT
        assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    void deveRetornarErro409QuandoUnicidadeException() {

        //ACT
        ResponseEntity<ExceptionResponse> resposta = tratadorDeErros.tratarErro409(unicidadeException, request);

        //ASSERT
        assertEquals(HttpStatus.CONFLICT, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

    @Test
    void deveRetornarErro500QuandoException(){

        //ACT
        ResponseEntity<ExceptionResponse> resposta = tratadorDeErros.tratarErro500(exception, request);

        //ASSERT
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, resposta.getStatusCode());
        assertNotNull(resposta.getBody());
    }

}
