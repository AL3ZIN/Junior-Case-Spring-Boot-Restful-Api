package com.itau.carros.application.core.usecase.usuario;

import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.core.validation.Validator;
import com.itau.carros.application.ports.in.usuario.CreateUsuarioUseCasePort;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUsuarioUseCaseImpl implements CreateUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private final List<Validator<Usuario>> validacoes;

    public CreateUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort, List<Validator<Usuario>> validacoes) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.validacoes = validacoes;
    }

    @Override
    public void cadastrar(Usuario usuario) {
        validacoes.forEach(v -> v.validar(usuario));
        usuarioRepositoryPort.save(usuario);
    }
}
