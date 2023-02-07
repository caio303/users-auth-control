package br.caio303.users_auth_control.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	private static final String SECRET = "My007TopSecureJWTSign";
	private static final Long EXPIRATION = (long) 300000;
	
	public static String generateToken(String cpf) {
		return Jwts.builder()
					.setSubject(cpf)
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
					.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
					.compact();
	}
	
	public static boolean isTokenValid(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	public static String getSubject(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private static Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
		
	}
}
