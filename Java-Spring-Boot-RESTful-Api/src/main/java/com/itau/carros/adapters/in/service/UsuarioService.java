package com.itau.carros.adapters.in.service;

import com.itau.carros.adapters.in.dto.usuario.UsuarioCadastroRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioLoginRequestDto;
import com.itau.carros.adapters.in.dto.usuario.UsuarioTokenResponseDto;
import com.itau.carros.adapters.in.mapper.UsuarioInMapper;
import com.itau.carros.application.ports.in.usuario.CreateUsuarioUseCasePort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final CreateUsuarioUseCasePort createUsuarioUseCasePort;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioInMapper mapper;

    public UsuarioService(CreateUsuarioUseCasePort createUsuarioUseCasePort, AuthenticationService authenticationService, PasswordEncoder passwordEncoder, UsuarioInMapper mapper) {
        this.createUsuarioUseCasePort = createUsuarioUseCasePort;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public UsuarioTokenResponseDto login(UsuarioLoginRequestDto dto){
       return mapper.toDto(authenticationService.login(mapper.toModel(dto))) ;
    }

    public void cadastrar(UsuarioCadastroRequestDto dto) {
        dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        createUsuarioUseCasePort.cadastrar(mapper.toModel(dto));
    }

}
