package com.victorbarreto.byte_bank.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.victorbarreto.byte_bank.entity.UsuarioModel;

@Service
public class TokenService {
    // Injeta o valor da nossa chave secreta do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UsuarioModel usuario) {
        try {
            // Define o algoritmo de assinatura com a nossa chave secreta
            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create().withIssuer("API ByteBank") // Identifica quem emitiu o token
                    .withSubject(usuario.getEmail()) // Identifica o usuário (usamos o e-mail)
                    .withExpiresAt(dataExpiracao()) // Define o tempo de expiração do token
                    .sign(algoritmo); // Assina o token
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    // Método para definir a data de expiração (ex: 2 horas a partir de agora)
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API ByteBank") // Verifica se o emissor é o mesmo
                    .build()
                    .verify(tokenJWT) // Valida o token (lança exceção se for inválido)
                    .getSubject(); // Retorna o "subject" (o e-mail do usuário)
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

}
