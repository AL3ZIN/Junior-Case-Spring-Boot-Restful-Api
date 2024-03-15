package com.itau.carros.adapters.out.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.*;


class TokenServiceTest {

    TokenService tokenService;
    private String secret;
    String email;

    @BeforeEach
    public void setUp() {
        secret = "12345";
        email = "teste@gmail.com";
        tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", secret);
    }

    @Test
    void deveGerarTokenValido() {

        String token = tokenService.gerarToken(email);

        var jwt = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("CarAPI")
                .build()
                .verify(token);

        assertAll(
                () -> assertThat(token).isNotNull().isNotEmpty(),
                () -> assertEquals(email, jwt.getSubject()),
                () -> assertEquals(token, jwt.getToken()),
                () -> assertEquals("CarAPI", jwt.getIssuer()),
                () -> assertThat(jwt.getExpiresAtAsInstant())
                        .isCloseTo(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")), within(1, ChronoUnit.SECONDS))
        );
    }


    @Test
    void deveValidarTokenComSucessoQuandoValido() {

        //ASSERT
        String token = JWT.create()
                .withSubject(email)
                .withIssuer("CarAPI")
                .sign(Algorithm.HMAC256(secret));

        //ACT + ASSERT
        var subject = assertDoesNotThrow(() -> tokenService.validarToken(token));
        assertEquals(email, subject);

    }


    @Test
    void naoDeveValidarTokenQuandoInvalido() {

        //ASSERT
        String token = JWT.create()
                .withSubject(email)
                .withIssuer("issuerErrado")
                .sign(Algorithm.HMAC256(secret));

        //ACT + ASSERT
        var exception = assertThrows(JWTVerificationException.class,() -> tokenService.validarToken(token));
        assertEquals("Token inválido ou expirado!", exception.getMessage());
    }

}