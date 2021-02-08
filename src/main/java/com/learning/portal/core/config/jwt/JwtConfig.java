package com.learning.portal.core.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtConfig {

    @Value("${jwt.token}")
    private String secretKey;

    public String extraUserName(String token){
        return extraClaims(token,Claims::getSubject);
    }

    public Date extraExpiration(String token){
        return extraClaims(token,Claims::getExpiration);
    }

    public <T> T extraClaims(String token, Function<Claims,T> claimsResolvers){
        final Claims claims = extraAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Claims extraAllClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extraExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    private String createToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 18000000L))
                .signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = extraUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
