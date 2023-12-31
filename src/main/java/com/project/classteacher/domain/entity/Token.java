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
    static Integer EXPIRATION_TIME;
    private String value;


    static {
        INSURER = getProperty("INSURER", "class-teacher");
        TOKEN_KEY = getProperty("TOKEN_KEY", "qwertyuiopasdfghjklzxcvbnm123456");
        EXPIRATION_TIME = Integer.parseInt(getProperty("EXPIRATION_TIME", "3600000"));
    }

    public static Token encode(User user) {
        String token = Jwts.builder()
                .setIssuer(INSURER)
                .setSubject(user.getName())
                .claim("id", user.getId())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole())
                .setExpiration(new Date(new Date().getTime() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return Token.builder().value(token).build();
    }

    public static DecodeToken decode(String token) throws InvalidTokenException {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(TOKEN_KEY.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            if (Boolean.TRUE.equals(isInvalidToken(claims))) throw new InvalidTokenException(token);
            return DecodeToken.builder()
                    .id(UUID.fromString(claims.get("id", String.class)))
                    .email(claims.get("email", String.class))
                    .name(claims.get("name", String.class))
                    .role(Roles.valueOf(claims.get("role", String.class)))
                    .approved(claims.get("approved", Boolean.class))
                    .build();
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
