package com.example.evoucherproject.auth.jwt;

import com.example.evoucherproject.auth.userdetails.CustomUserDetails;
import com.example.evoucherproject.exception.CustomException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    @Value("${secret.key}")
    private String SECRET_KEY;

    //Thời gian có hiệu lực của chuỗi jwt 1 day
    private static final long EXPIRATION_TIME = 86400000; // 1 day

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // xác thực token
    public boolean validateToken(String token) {
        boolean isToken = false;
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            isToken =  true;
        } catch (SignatureException ex) {

        } catch (ExpiredJwtException ex) {

        } catch (MalformedJwtException ex) {

        } catch (UnsupportedJwtException ex) {

        } catch (IllegalArgumentException ex) {

        }
        return isToken;
    }
    public void validateTokenException(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
        } catch (SignatureException ex) {
            // Invalid JWT signature
            throw new CustomException("Invalid JWT signature", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException ex) {
            // Expired JWT token
            throw new CustomException("Expired JWT token", HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException ex) {
            // Invalid JWT token
            throw new CustomException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException ex) {
            // Unsupported JWT token
            throw new CustomException("Unsupported JWT token", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException ex) {
            // JWT claims string is empty
            throw new CustomException("JWT claims string is empty", HttpStatus.UNAUTHORIZED);
        }
    }

    // Lấy thông tin user từ jwt

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
