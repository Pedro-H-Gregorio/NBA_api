package br.com.nba.api.services.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.nba.api.entities.User;
import br.com.nba.api.services.interfaces.TokenService;

@Service
public class JwtTokenService implements TokenService {
    @Value("${spring.security.token.secret}")
    private String secret;
    @Value("${spring.security.token.issuer}")
    private String issuer;

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(creationDate())
                .withExpiresAt(expirationDate())
                .withSubject(user.getUsername())
                .sign(algorithm);
    }

    public String getSubjectFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.systemDefault()).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.systemDefault()).plusHours(2).toInstant();
    }
}
