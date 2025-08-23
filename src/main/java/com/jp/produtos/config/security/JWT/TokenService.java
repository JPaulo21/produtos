package com.jp.produtos.config.security.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final static Logger log = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.key}")
    private String secretKey;

    public String generateToken(){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("produtos-api")
                .withSubject("user")
                .withExpiresAt(expirationToken())
                .sign(algorithm);
    }

    public boolean validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("produtos-api")
                    .build()
                    .verify(token)
                    .getSubject().equals("user");
        } catch(JWTVerificationException e){
            log.warn("Token inválido");
            return false;
        }
    }

    private Instant expirationToken() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
