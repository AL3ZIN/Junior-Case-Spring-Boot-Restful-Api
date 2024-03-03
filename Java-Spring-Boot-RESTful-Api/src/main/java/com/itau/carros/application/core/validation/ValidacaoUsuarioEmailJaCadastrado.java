package com.itau.carros.application.core.validation;

import com.itau.carros.application.core.exception.UnicidadeException;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuarioEmailJaCadastrado implements Validator<Usuario>{

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ValidacaoUsuarioEmailJaCadastrado(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public void validar(Usuario model) {
        boolean dadosJaCadastrados = usuarioRepositoryPort.existsByEmail(model.getEmail());
        if (dadosJaCadastrados){
            throw new UnicidadeException("Email ja cadastrado em nosso banco de dados.");
        }
    }
}
