package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cadastro")
@Tag(name = "Cadastro", description = "Endpoints para o cadastramento de novos usuários.")
public class CadastroContoler {

    private final UsuarioService usuarioService;

    public CadastroContoler(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid UsuarioCadastroRequestDto dto){
        usuarioService.cadastrar(dto);
        return ResponseEntity.ok().build();
    }
}
