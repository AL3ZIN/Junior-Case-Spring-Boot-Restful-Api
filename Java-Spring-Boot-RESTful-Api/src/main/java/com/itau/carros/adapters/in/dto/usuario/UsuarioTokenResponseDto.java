package com.itau.carros.adapters.in.dto.usuario;

public class UsuarioTokenResponseDto {

    private String token;

    public UsuarioTokenResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
