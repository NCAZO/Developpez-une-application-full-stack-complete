package com.openclassrooms.mddapi.services;

import java.security.Key;
import java.util.Date;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
  private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

//  @Value("${op.app.jwtSecret}")
  private String jwtSecret = "lriAc1p6y27mg+Cy5TpDBh3USQ2CnFedOjp2kj9PE/ICFr6maDoahCPG2kGnsAFWhLANySppkBbHJxPyoIdchPXpP2WMHERZNY5LBCmmMj8B7N7TulyFTjLM72TC9JCGZe0ToYfIe5QNTeqzjU0wR1F2jhHdFBRrU7wikMEYoFoP7Mx8uJRxjotW4x2ngcM0XMKnoK6TgGyQ8IdoUI359Ain0BbrhrQXlZP5VeoPeXfrdz0UyeUQqTTwAok0zs4g4mBfxYm+W+2L9uc8H/mqkMfMcLjDzm+dLxxZRRlGdY3y3ctOpmvbuoTbCwlQ3rtlT1EYo2eg6cyn0//8RCKkjvmjVr2xsXNqSlr6aIxP66I=";

//  @Value("${op.app.jwtExpirationMs}")
  private int jwtExpirationMs = 86400000;

//  @Value("${op.app.jwtCookieName}")
  private String jwtCookie = "nicolas";

  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) {
      return cookie.getValue();
    } else {
      return null;
    }
  }

  public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
    String jwt = generateTokenFromUsername(userPrincipal.getUsername());
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60 * 60).httpOnly(true).build();
    return cookie;
  }

  public ResponseCookie getCleanJwtCookie() {
    ResponseCookie cookie = ResponseCookie.from(jwtCookie, null).path("/api").build();
    return cookie;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }

  public String generateTokenFromUsername(String username) {   
    return Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
               .signWith(key(), SignatureAlgorithm.HS256)
               .compact();
  }
}
