package com.dh.digitalBooking.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService implements IJwtService{

    private final String SECRET_KEY = "secret";

    @Override
    public String extractUserName(String token) {
        return extractClaimUsername(token);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaimDate(token);
    }

    @Override
    public Date extractClaimDate(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    @Override
    public String extractClaimUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(now.getTime() + 100 * 60 * 1000))// 100 minutos
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String expireToken(String token) {
        long currentTime = System.currentTimeMillis();
        String newJwtToken = Jwts.builder()
                .setExpiration(new Date(currentTime - 60000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return newJwtToken;
    }



//        Date now = new Date();
//        String nombreUsuario = extractUserName(token);
//        if(!isTokenExpired(token)){
//            Jwts.builder().setClaims(new HashMap<>()).setSubject(nombreUsuario).setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(now.getTime()));
//        }
//
//
//        return "El token ya ha expirado";
//        return Jwts.builder().setClaims(new HashMap<>()).setSubject(extractClaimUsername(token)).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(now.getTime()))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    //}

}
