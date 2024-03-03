package com.itau.carros.application.ports.out;

import com.itau.carros.application.core.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {
    void save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNome(String nome);
}
