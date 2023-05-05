package com.douglasbello.demo.config;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.douglasbello.demo.entities.User;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class MyToken {

    private static final String ISSUER = "douglasbello";
    private static final String TOKEN_HEADER = "Bearer ";
    private static final long ONE_SEC = 1000;
    private static final String TOKEN_KEY = "1234567890123456789012345678901234";
    private static final long ONE_MINUTE = 60*ONE_SEC;

    public static AuthToken encodeToken(User user) {
	Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
	String tokenJWT = Jwts.builder().setSubject(user.getUsername())
	    .setIssuer(ISSUER)
	    .setExpiration(new Date(System.currentTimeMillis() + ONE_MINUTE))
	    .signWith(secretKey, SignatureAlgorithm.HS256)
	    .compact();

		return new AuthToken(TOKEN_HEADER + tokenJWT);
    }

    public static Authentication decodeToken(HttpServletRequest request) {
	String jwtToken = request.getHeader("Authorization");
	jwtToken = jwtToken.replace(TOKEN_HEADER, "");

	// decoding the token
	Jws<Claims> jwsClaims = Jwts.parserBuilder()
	    .setSigningKey(TOKEN_KEY.getBytes())
	    .build()
	    .parseClaimsJws(jwtToken);

	// extracting the information
	String user = jwsClaims.getBody().getSubject();
	String issuer = jwsClaims.getBody().getIssuer();
	Date validity = jwsClaims.getBody().getExpiration();

	if (user.length() > 0 && issuer.equals(ISSUER) && validity.after(new Date(System.currentTimeMillis()))) {
	    return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
	}



	return null;
    }
}

