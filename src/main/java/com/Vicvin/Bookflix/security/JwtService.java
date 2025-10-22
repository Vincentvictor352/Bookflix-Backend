package com.Vicvin.Bookflix.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Base64;

@Service
public class JwtService {

    private SecretKey key;

    @Value("${jwt.secret}")
    private String secretBase64; // should be base64 string

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    @PostConstruct
    public void init() {
        byte[] decoded = Base64.getDecoder().decode(secretBase64);
        this.key = Keys.hmacShaKeyFor(decoded);
    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
