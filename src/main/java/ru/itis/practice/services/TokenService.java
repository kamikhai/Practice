package ru.itis.practice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import ru.itis.practice.models.User;

public interface TokenService {

    String getToken(User user);
}
