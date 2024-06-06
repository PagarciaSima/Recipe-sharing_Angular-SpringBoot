package com.recipe.sharing.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	private SecretKey key;
    
    // Constructor
    public JwtProvider() {
        try {
            this.key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
            // Keys.secretKeyFor(SignatureAlgorithm.HS256);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar la clave secreta", e);
         
        }
    }
    
	public String generateToken(Authentication auth) {
		String jwt = Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 86400000))
				.claim("email", auth.getName())
				.signWith(key).compact();
				
		return jwt;
	}
	
	public String getEmailFromJwtToken(String jwt) {
		// Remove "Bearer" from Bearer jwt
		jwt = jwt.substring(7);
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		return email;
	}
}
