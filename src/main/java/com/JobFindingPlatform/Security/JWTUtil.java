package com.JobFindingPlatform.Security;

import java.util.Date;
import java.security.Key;

import org.springframework.stereotype.Component;

import com.JobFindingPlatform.Entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

    // Secret must be at least 32 bytes for HS512
    private final String secret = "jobfindingKeyjobfindingKeyjobfindingKey123456"; 
    private final long expirationTime = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(secret.getBytes());

    // Generate JWT token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserEmail())
                .claim("role", user.getRole().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key) // only key is needed
                .compact();
    }

    // Extract username/email from token
    public String extractUserEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    // Validate token
    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
