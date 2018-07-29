package com.web2team.security.util;

import com.web2team.security.transfer.JwtUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenGenerator {

  public static String generateToken(JwtUserDto u, String secret) {
    Claims claims = Jwts.claims().setSubject(u.getUsername());
    claims.put("role", u.getRole());

    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, secret)
        .setExpiration(DateTime.now().plusDays(1).toDate())
        .compact();
  }
}
