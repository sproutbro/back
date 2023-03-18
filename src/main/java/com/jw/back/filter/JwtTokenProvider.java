package com.jw.back.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "mySecretKddshfuioncyweonc7o8c3ny812n648721n6387126n89127389217n389n72189rniewfhiodsjfionxvphdiapohjio127u89123eysdfsddfjdsiofjsdoipfj2j8d89f1112"; // JWT secret key

    // JWT 토큰 생성
    public String createToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // JWT 만료 시간 1시간

        // JWT payload에 저장할 정보
        Claims claims = Jwts.claims().setSubject(userId);

        // JWT 토큰 생성
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
