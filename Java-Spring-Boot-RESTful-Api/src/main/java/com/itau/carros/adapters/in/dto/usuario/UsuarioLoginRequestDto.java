package com.itau.carros.adapters.in.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginRequestDto {

    @Email(message = "Formato de email invalido")
    @NotBlank
    @Size(min = 5, max = 254, message = "O e-mail deve ter entre 4 e 254 caracteres.")
    private String email;
    @NotBlank
    @Size(min = 6, max = 20, message = "Senha tem que estar entre 6 e 20 caracteres")
    private String senha;

    public UsuarioLoginRequestDto() {
    }

    public UsuarioLoginRequestDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
