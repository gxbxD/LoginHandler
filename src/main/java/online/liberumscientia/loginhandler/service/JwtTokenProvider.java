package online.liberumscientia.loginhandler.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final Key key;

    // Gera uma chave segura de 256 bits automaticamente
    @SuppressWarnings("deprecation")
    public JwtTokenProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Gera uma chave segura para HS256
    }

    @SuppressWarnings("deprecation")
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @SuppressWarnings("deprecation")
    public Claims parseToken(String token) throws JwtException {
        JwtParserBuilder parserBuilder = Jwts.parser()
                .setSigningKey(key); // Define a chave de assinatura
        Jws<Claims> claimsJws = parserBuilder.build().parseClaimsJws(token); // Parse e valida o token
        return claimsJws.getBody();
    }
}
