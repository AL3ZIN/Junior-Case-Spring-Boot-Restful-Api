package com.itau.carros.adapters.in.mapper;

import com.itau.carros.adapters.in.dto.usuario.CustomUserDetails;
import com.itau.carros.application.core.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class SecurityMapper {

    public UsernamePasswordAuthenticationToken toAuthenticationToken(Usuario model){
        var customDetails = toCustomUserDetails(model);
        return new UsernamePasswordAuthenticationToken(customDetails, model.getSenha(), customDetails.getAuthorities());
    }

    public CustomUserDetails toCustomUserDetails(Usuario usuario){
        return new CustomUserDetails(usuario);
    }
}
