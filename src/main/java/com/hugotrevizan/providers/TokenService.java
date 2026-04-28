package com.hugotrevizan.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class TokenService {

    @Value("${security.token.secret}")
    private String secretKey;

    private static final String ISSUER = "javagas";

    public String generateToken(String subject, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(subject)
                .withClaim("roles", roles)
                .withExpiresAt(expiresIn)
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        var tokenLimpo = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenLimpo);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}