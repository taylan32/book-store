package com.example.bookstore.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class TokenGenerator {

	@Value("${jwt-variables.KEY}")
	private String KEY;
	
	@Value("${jwt-variables.ISSUER}")
	private String ISSUER;
	
	@Value("${jwt-variables.EXPRIES_ACCESS_TOKEN_MINUTE}")
	private long EXPIRES_ACCESS_TOKEN_MINUTE;
	
	public String generateToken(Authentication authentication) {
		String userName = ((UserDetails)authentication.getPrincipal()).getUsername();
		return JWT.create()
				.withSubject(userName)
				.withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE*60*1000)))
				.withIssuer(ISSUER)
				//.withClaim("name", "value") // role gibi extra olrak eklemek istediklerimiz
				.sign(Algorithm.HMAC256(KEY.getBytes()));
	}
	
			
	public DecodedJWT verifyJwt(String token) throws Exception {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY.getBytes())).build();
		try {
			var decodedToken = verifier.verify(token);
			//var claimName = decodedToken.getClaim().get("claimName");
			return decodedToken;
		} catch(Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}
	
}
