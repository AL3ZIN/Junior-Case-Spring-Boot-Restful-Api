package com.itau.carros.adapters.in.filter;

import com.itau.carros.adapters.in.mapper.SecurityMapper;
import com.itau.carros.adapters.out.security.TokenService;
import com.itau.carros.application.core.model.Usuario;
import com.itau.carros.application.ports.in.usuario.GetUsuarioUseCasePort;
import com.itau.carros.application.ports.out.UsuarioRepositoryPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final GetUsuarioUseCasePort getUsuarioUseCasePort;
    private final SecurityMapper securityMapper;

    public SecurityFilter(TokenService tokenService, UsuarioRepositoryPort usuarioRepositoryPort, GetUsuarioUseCasePort getUsuarioUseCasePort, SecurityMapper securityMapper) {
        this.tokenService = tokenService;
        this.getUsuarioUseCasePort = getUsuarioUseCasePort;
        this.securityMapper = securityMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            System.out.println("Token: " + tokenJWT);
            var subject = tokenService.validarToken(tokenJWT);
            var usuario = getUsuarioUseCasePort.loadUserByUsername(subject);
            autenticarUsuario(usuario);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private void autenticarUsuario(Usuario usuario){
        SecurityContextHolder.getContext().setAuthentication(
                securityMapper.toAuthenticationToken(usuario));
    }

}