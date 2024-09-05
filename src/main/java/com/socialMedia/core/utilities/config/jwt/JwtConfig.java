package com.socialMedia.core.utilities.config.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.socialMedia.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtConfig {

	@Value("${SECRET_KEY}")
	private String KEY;

	public String createToken(User user) {
		JwtBuilder builder = Jwts.builder();

		builder = builder.subject(user.getUsername());

		Instant date = Instant.now().plus(45, ChronoUnit.MINUTES);

		builder = builder.subject("login").id(user.getEmail()).issuedAt(new Date()).expiration(Date.from(date));

		return builder.signWith(getKey()).compact();

	}

	public String generateAuthToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.putAll(Map.of("email", user.getEmail(), "username", user.getUsername()

		));
		return generateToken(claims, user);
	}

	private String generateToken(Map<String, Object> extraClaims, User userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getKey(), SignatureAlgorithm.HS256).compact();
	}

	public Claims tokenControl(String token) {
		JwtParser builder = Jwts.parser().verifyWith(getKey()).build();
		return builder.parseSignedClaims(token).getPayload();
	}

	private SecretKey getKey() {
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
		return key;
	}

	public static void main(String[] args) {
		createKey();
	}

	public static void createKey() {
		SecretKey key = Jwts.SIG.HS512.key().build();
		String str = Encoders.BASE64.encode(key.getEncoded());
		System.out.println(str);
	}
}
