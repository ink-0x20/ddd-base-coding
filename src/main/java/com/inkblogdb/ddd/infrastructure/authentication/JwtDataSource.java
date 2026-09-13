package com.inkblogdb.ddd.infrastructure.authentication;

import com.inkblogdb.ddd.domain.model.authentication.AuthenticationExpiration;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationRepository;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationToken;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Repository
public class JwtDataSource implements AuthenticationRepository {

  private final SecretKey secretKey;

  public JwtDataSource(@Value("${jwt.secret-key}") String jwtSecretKey) {
    secretKey = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public AuthenticationToken login(PlayerId playerId, AuthenticationExpiration authenticationExpiration) {
    return new AuthenticationToken(
        Jwts.builder()
            .subject(playerId.value())
            .issuedAt(new Date(authenticationExpiration.attempt()))
            .expiration(new Date(authenticationExpiration.expiration()))
            .signWith(secretKey)
            .compact()
    );
  }

  @Override
  public PlayerId extractPlayerId(AuthenticationToken authenticationToken) {
    return new PlayerId(
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(authenticationToken.value())
            .getPayload()
            .getSubject()
    );
  }

  @Override
  public boolean validateToken(AuthenticationToken authenticationToken) {
    try {
      Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(authenticationToken.value());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
