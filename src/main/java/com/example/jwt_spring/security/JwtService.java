package com.example.jwt_spring.security;

import com.example.jwt_spring.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private long jwtExpiration;

  public String generateToken(User user) {
    return Jwts.builder()
      .setSubject(user.getEmail())
      .claim("role", user.getRole().name())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
      .compact();
  }

  private Key getSignKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(getSignKey())
      .build()
      .parseClaimsJws(token)
      .getBody()
      .getSubject();
  }

}
