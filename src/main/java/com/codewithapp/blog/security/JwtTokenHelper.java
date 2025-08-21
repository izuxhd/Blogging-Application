
package com.codewithapp.blog.security;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
@Component

public class JwtTokenHelper {
	public  static      final   long JWT_TOKEN_VALIDITY=5*60*60;
    private static final String secret = "nikhilTokenkey";
    
    //username  lene  ke  liye
    public String getUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    //expiration  of   token  from token
    public Date getExpirationDatefromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    private String doGenerateToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @SuppressWarnings("deprecation")
	private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpired(String token) {
    	final Date  expiration=getExpirationDatefromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails  userDetails) { // Use email as username
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}