package com.mangoplate.mangoplate.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtils {

    public static String getUserId(String token, String key) {
        return extractClaims(token, key).get("userId", String.class);
    }

    public static boolean isExpired(String token, String key) {
        Date date = extractClaims(token, key).getExpiration();
        return date.before(new Date());
    }

    private static Claims extractClaims(String token, String key) {
        return Jwts.parserBuilder().setSigningKey(getKey(key))
                .build().parseClaimsJws(token).getBody();
    }


    public static String generateToken(String userId, String key, long expiredTimeMs) {
        Claims clamis = Jwts.claims();
        clamis.put("userId", userId);
        return Jwts.builder()
                .setClaims(clamis)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeMs))
                .signWith(SignatureAlgorithm.HS256, getKey(key))
                .compact();
    }

    public static Key getKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
