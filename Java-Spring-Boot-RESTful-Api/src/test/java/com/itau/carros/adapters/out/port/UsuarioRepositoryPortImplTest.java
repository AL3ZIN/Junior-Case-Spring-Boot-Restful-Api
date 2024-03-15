package com.itau.carros.adapters.out.port;

import com.itau.carros.adapters.out.persistence.entity.UsuarioEntity;
import com.itau.carros.adapters.out.persistence.mapper.UsuarioOutMapper;
import com.itau.carros.adapters.out.persistence.repository.UsuarioRepository;
import com.itau.carros.application.core.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryPortImplTest {

    @InjectMocks
    UsuarioRepositoryPortImpl usuarioRepositoryPortImpl;

    @Mock
    UsuarioRepository usuarioRepository;

    @Mock
    UsuarioOutMapper mapper;

    Usuario usuario;

    UsuarioEntity usuarioEntity;


    @BeforeEach
    void setUp() {
        usuario = new Usuario(
                "emailteste@gmail.com",
                "senhateste",
                "teste"
        );

        usuarioEntity = new UsuarioEntity(
                "emailteste@gmail.com",
                "senhateste",
                "teste"
        );
    }

    @Test
    void DeveChamarRepositorioQuandoSalvarUsuario() {

        //ARRANGE
        when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(mapper.toEntity(usuario)).thenReturn(usuarioEntity);

        //ACT
        usuarioRepositoryPortImpl.save(usuario);

        //ASSERT
        verify(mapper, times(1)).toEntity(usuario);
        verify(usuarioRepository, times(1)).save(usuarioEntity);
    }

    @Test
    void DeveChamarRepositorioQuandoListarUsuarioPorEmail() {

        //ARRANGE
        when(usuarioRepository.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuarioEntity));
        when(mapper.toModel(usuarioEntity)).thenReturn(usuario);

        //ACT
        usuarioRepositoryPortImpl.findByEmail(usuario.getEmail());

        //ASSERT
        verify(mapper, times(1)).toModel(usuarioEntity);
        verify(usuarioRepository, times(1)).findByEmail(usuario.getEmail());
    }

    @Test
    void DeveChamarRepositorioQuandoValidarUsuarioPorEmail(){

        //ARRANGE
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        //ACT + ASSERT
        assertTrue(usuarioRepositoryPortImpl.existsByEmail(usuario.getEmail()));
        verify(usuarioRepository, times(1)).existsByEmail(usuario.getEmail());
    }

    @Test
    void DeveChamarRepositorioQuandoValidarUsuarioPorNome(){

        //ARRANGE
        when(usuarioRepository.existsByNome(usuario.getNome())).thenReturn(true);

        //ACT + ASSERT
        assertTrue(usuarioRepositoryPortImpl.existsByNome(usuario.getNome()));
        verify(usuarioRepository, times(1)).existsByNome(usuario.getNome());
    }
}
