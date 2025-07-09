package com.moecodes.xperienceserver.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String secretKey;
    @Value("${app.jwt.expiration-ms}")
    private Long validityMs;

    // TODO: Fix this
    public String generateToken(UserDetails user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityMs);
        return Jwts.builder().subject(user.getUsername())
                .claim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList())
                .issuedAt(now)
                .expiration(expiry);
                // .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), );
    }
}
