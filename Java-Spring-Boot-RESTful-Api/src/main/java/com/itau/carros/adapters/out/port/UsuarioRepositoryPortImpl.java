package com.itau.carros.adapters.out.port;

import com.itau.carros.adapters.out.persistence.mapper.UsuarioOutMapper;
import com.itau.carros.adapters.out.persistence.repository.UsuarioRepository;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryPortImpl implements UsuarioRepositoryPort {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioOutMapper mapper;

    public UsuarioRepositoryPortImpl(UsuarioRepository usuarioRepository, UsuarioOutMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(mapper.toEntity(usuario));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(mapper::toModel);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNome(String nome) {
        return usuarioRepository.existsByNome(nome);
    }
}
