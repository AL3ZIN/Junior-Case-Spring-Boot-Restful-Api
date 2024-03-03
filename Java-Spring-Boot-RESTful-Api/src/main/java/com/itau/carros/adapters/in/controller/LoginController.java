package com.itau.carros.adapters.in.controller;

import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioTokenResponseDto;
import com.itau.carros.adapters.in.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@Tag(name = "Login", description = "Endpoints para autenticação e geração de tokens de usuários cadastrados")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioTokenResponseDto> login(@RequestBody @Valid UsuarioLoginRequestDto dto){
        return ResponseEntity.ok(usuarioService.login(dto));
    }
}
