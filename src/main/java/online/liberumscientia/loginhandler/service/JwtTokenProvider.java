package online.liberumscientia.loginhandler.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtTokenProvider {
    private final Key key; // chave para assinatura do JWT

    public JwtTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // configura a chave usando o segredo
    }

    @SuppressWarnings("deprecation")
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256) // assinatura com chave
                .compact();
    }

    @SuppressWarnings("deprecation")
    public Claims parseToken(String token) throws JwtException {
        JwtParser parser = ((JwtParserBuilder) Jwts.builder())
                .setSigningKey(key)
                .build();

        Jws<Claims> claimsJws = parser.parseClaimsJws(token); // parse e valida o token
        return claimsJws.getBody(); // retorna as reivindicações (claims)
    }
}
