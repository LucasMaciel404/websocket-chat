package com.lucasmaciel.websocket_chat.config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secretExemplo:}")
    private String secret;

    @Bean
    public Algorithm jwtAlgorithm() {
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("Propriedade jwt.secret não configurada");
        }
        return Algorithm.HMAC256(secret);
    }

    @Bean
    public JWTVerifier jwtVerifier(Algorithm algorithm) {
        return JWT.require(algorithm).build();
    }
}
