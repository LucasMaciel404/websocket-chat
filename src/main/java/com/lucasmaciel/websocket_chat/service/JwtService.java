package com.lucasmaciel.websocket_chat.service;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JWTVerifier verifier;

    public JwtService(JWTVerifier verifier) {
        this.verifier = verifier;
    }

    public boolean isValid(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject(); // pega "sub"
    }
}
