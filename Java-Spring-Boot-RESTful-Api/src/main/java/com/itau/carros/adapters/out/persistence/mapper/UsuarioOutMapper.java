package com.itau.carros.adapters.out.persistence.mapper;

import com.itau.carros.adapters.out.persistence.entity.UsuarioEntity;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class UsuarioOutMapper {

    public Usuario toModel(UsuarioEntity entity){
        var model = new Usuario();
        copyProperties(entity, model);
        return model;
    }

    public UsuarioEntity toEntity(Usuario model) {
        var entity = new UsuarioEntity();
        copyProperties(model, entity);
        return entity;
    }
}
