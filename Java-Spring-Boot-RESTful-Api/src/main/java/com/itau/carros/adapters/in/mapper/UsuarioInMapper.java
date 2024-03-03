package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioTokenResponseDto;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class UsuarioInMapper {
    
    public Usuario toModel(UsuarioCadastroRequestDto dto) {
        var model = new Usuario();
        copyProperties(dto,model);
        return model;
    }

    public UsuarioTokenResponseDto toDto(String token) {
        return new UsuarioTokenResponseDto(token);
    }

    public Usuario toModel(UsuarioLoginRequestDto dto) {
        var model = new Usuario();
        copyProperties(dto,model);
        return model;
    }
}
