package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

public interface AuthenticationRepository {

  AuthenticationToken login(PlayerId playerId, AuthenticationExpiration authenticationExpiration);

  PlayerId extractPlayerId(AuthenticationToken authenticationToken);

  boolean validateToken(AuthenticationToken authenticationToken);

}
