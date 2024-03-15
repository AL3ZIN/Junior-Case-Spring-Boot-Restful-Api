package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.UsuarioEntity;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioOutMapper {

    public Usuario toModel(UsuarioEntity entity) {
        return new Usuario(
                entity.getEmail(),
                entity.getSenha(),
                entity.getNome()
        );
    }

    public UsuarioEntity toEntity(Usuario model) {
        return new UsuarioEntity(
                model.getEmail(),
                model.getSenha(),
                model.getNome()
        );
    }
}
