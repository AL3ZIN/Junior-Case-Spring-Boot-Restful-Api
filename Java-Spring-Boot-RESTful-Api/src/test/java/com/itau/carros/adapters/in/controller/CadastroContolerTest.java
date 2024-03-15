package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CadastroContolerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private JacksonTester<UsuarioCadastroRequestDto> usuarioCadastroRequestDtoJson;

    private UsuarioCadastroRequestDto usuarioCadastroRequestDto;

    @BeforeEach
    public void setUp(){
        usuarioCadastroRequestDto = new UsuarioCadastroRequestDto(
                "emailteste@gmail.com",
                "teste",
                "senhateste"

        );
    }


    @Test
    void deveEfetuarCadastro() throws Exception {
        mockMvc.perform(post("/api/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioCadastroRequestDtoJson.write(usuarioCadastroRequestDto).getJson())
        ).andExpect(status().isOk());
    }
}