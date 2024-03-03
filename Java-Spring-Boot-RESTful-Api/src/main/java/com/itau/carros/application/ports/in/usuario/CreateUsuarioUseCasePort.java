package com.itau.carros.application.ports.in.usuario;

import com.itau.carros.application.core.model.Usuario;

public interface CreateUsuarioUseCasePort {
   void cadastrar(Usuario usuario);
}
