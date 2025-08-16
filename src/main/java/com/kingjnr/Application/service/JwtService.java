package com.kingjnr.Application.service;

import com.kingjnr.Application.model.UserLoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey = "YOURBASE64SECRETGIOUYMNTFCVJNYIMYNVRUF5RF4DKEY";


    public String generateToken(UserLoginDTO userDTO) {

        HashMap<String, Object> claims = new HashMap<>();

        return Jwts.
                builder().
                setClaims(claims).
                setSubject(userDTO.getEmail()).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000)).
                signWith(getKey(), SignatureAlgorithm.HS256).
                compact();
    }


    public Key getKey(){
        byte [] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public boolean isValidToken(String token) {
        return !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaims(Claims::getSubject,token);
    }

    public <T> T extractClaims(Function<Claims,T> claimsResolver, String token ){

        return claimsResolver.apply(extractAllClaims(token));
    }

    public Claims extractAllClaims(String token){

        return Jwts.
                parserBuilder().
                setSigningKey(getKey()).
                build().
                parseClaimsJws(token).
                getBody();

    }

    public boolean isTokenExpired(String token){
        return extractClaims(Claims::getExpiration, token).before(new Date(System.currentTimeMillis()));
    }
}
