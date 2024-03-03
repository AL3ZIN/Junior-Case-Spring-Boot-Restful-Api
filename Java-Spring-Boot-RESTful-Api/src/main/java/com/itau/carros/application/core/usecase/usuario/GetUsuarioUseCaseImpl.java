package com.itau.carros.application.core.usecase.usuario;

import com.itau.carros.application.core.exception.ConsultaNulaException;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.in.usuario.GetUsuarioUseCasePort;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class GetUsuarioUseCaseImpl implements GetUsuarioUseCasePort{

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public GetUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    public Usuario loadUserByUsername(String email) {
        return usuarioRepositoryPort.findByEmail(email).orElseThrow(()-> new ConsultaNulaException("Não foi encontrado nenhum usuario com esse email."));
    }
}
