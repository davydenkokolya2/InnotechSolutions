package by.innotechsolutions.practice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;


@PropertySource(value = {"classpath:application.properties"})
@Service
public class JwtTokenService {

    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(10);

    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}")  final String secret) {
        System.out.println(secret);
        this.hmac512 = Algorithm.HMAC512(secret);
        System.out.println(this.hmac512);
        this.verifier = JWT.require(this.hmac512).build();
        System.out.println(this.verifier);
    }

    public String generateToken(final UserDetails userDetails) {
        final Instant now = Instant.now();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            System.out.println(token);
            System.out.println(verifier.verify(token).getSubject());
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException ex) {
            return null;
        }
    }
}
