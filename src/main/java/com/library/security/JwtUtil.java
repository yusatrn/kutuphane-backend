package com.library.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

@Component
public class JwtUtil {
    private final Key anahtar = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long gecerlilikSuresiMs = 3600000; // 1 saat
    
    public String tokenOlustur(String eposta, Set<String> roller) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", new ArrayList<>(roller));

        Date simdi = new Date();
        Date gecerlilikSuresi = new Date(simdi.getTime() + gecerlilikSuresiMs);

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(eposta)
            .setIssuedAt(simdi)
            .setExpiration(gecerlilikSuresi)
            .signWith(anahtar)
            .compact();
    }

    public String epostaGetir(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(anahtar)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean tokenGecerliMi(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(anahtar)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Token'dan kullanıcı ID'sini almak için yeni metod
    public String getIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(anahtar)
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        return claims.get("id", String.class);
    }

    // Token'dan rolleri almak için yeni metod
    @SuppressWarnings("unchecked")
    public Set<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(anahtar)
            .build()
            .parseClaimsJws(token)
            .getBody();
        
        List<String> roleList = claims.get("roles", List.class);
        return new HashSet<>(roleList);
    }
} 