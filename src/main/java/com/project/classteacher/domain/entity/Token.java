package com.project.classteacher.domain.entity;

import com.project.classteacher.application.exceptions.InvalidTokenException;
import com.project.classteacher.domain.enums.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.getProperty;

@Data
@Getter
@Builder
public class Token {

    static String INSURER;
    static String TOKEN_KEY;
    static String EXPIRATION_TIME;


    static {
        INSURER = getProperty("jwt.ensurer", "class-teacher");
        TOKEN_KEY = getProperty("jwt.token_key", "qwertyuiopasdfghjklzxcvbnm123456");
        EXPIRATION_TIME = getProperty("jwt.expiration_time", "3600000");
    }

    private String token;

    public static Token encode(User user) {
        String token = Jwts.builder()
                .setIssuer(INSURER)
                .setSubject(user.getName())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(EXPIRATION_TIME)))
                .signWith(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return Token.builder().token(token).build();
    }

    public static User decode(String token) throws InvalidTokenException {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            if (isInvalidToken(claims)) throw new InvalidTokenException(token);
            return new User(
                    UUID.fromString(claims.get("id").toString()),
                    claims.get("name").toString(),
                    claims.get("email").toString(),
                    null,
                    Roles.valueOf(claims.get("role").toString()),
                    null
            );
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    private static Boolean isInvalidToken(Claims claims) {
        String insurer = claims.getIssuer();
        Date expiration = claims.getExpiration();
        return !insurer.equals(INSURER) && !expiration.after(new Date(currentTimeMillis()));
    }
}
