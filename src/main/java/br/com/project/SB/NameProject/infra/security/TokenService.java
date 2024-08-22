package br.com.project.SB.NameProject.infra.security;

import br.com.project.SB.NameProject.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private Instant expirationToken() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateToken(User user) {
        try {
            final var userRoles = user.getRole().getRole(); // Verifique se isso está correto e retorna a lista de papéis
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("logicflare-sebrae")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationToken())
                    .withClaim("roles", userRoles)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while token generation " + e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("logicflare-sebrae")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error while token verification " + e);
        }
    }

}
