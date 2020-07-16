package ru.itis.practice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.User;

@Service
public class TokenServiceImpl implements TokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String getToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("name", user.getFullName())
                .claim("role", user.getRole().name())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return token;
    }
}
