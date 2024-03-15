package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioTokenResponseDto;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInMapper {
    
    public Usuario toModel(UsuarioCadastroRequestDto dto) {
        return new Usuario(
                dto.getEmail(),
                dto.getSenha(),
                dto.getNome()
        );
    }

    public Usuario toModel(UsuarioLoginRequestDto dto) {
        return new Usuario(
                dto.getEmail(),
                dto.getSenha(),
                null
        );
    }

    public UsuarioTokenResponseDto toDto(String token) {
        return new UsuarioTokenResponseDto(token);
    }


}
