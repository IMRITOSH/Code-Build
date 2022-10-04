package com.spring.codebuild.Helpers;

import com.spring.codebuild.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JWT {

    public JWT() {

    }


    public String generateToken(Map<String, Object> claims) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(10).atZone(ZoneId.systemDefault())
                .toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    public String getClaimFromToken(String token, String claim) {
        Map<String, Object> claims = getAllClaimsFromToken(token);
        return claims.get(claim).toString();
    }

    public boolean validateVersion(String token, User dataBaseUser) {
        int version = Integer.valueOf(getClaimFromToken(token, "Version"));


        if (version == dataBaseUser.getVersion()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateToken(@NonNull String token) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("TOKEN ERROR");
        }
        return false;
    }
}
