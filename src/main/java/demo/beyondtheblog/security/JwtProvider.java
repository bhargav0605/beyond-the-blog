package demo.beyondtheblog.security;

import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtProvider {
	
	private Key key;
	@PostConstruct
	public void init() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
	
	
	public String generateToken(Authentication authentication) {
		User principle = (User)authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(principle.getUsername())
				.signWith(key)
				.compact();
	}
	
	boolean validateToken(String jwt){
		Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
		return true;
	}


	public String getUsernameFromJWT(String token) {
		// TODO Auto-generated method stub
		
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
		
	}

}
