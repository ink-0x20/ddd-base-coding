package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

public record Authentication(PlayerId playerId, AuthenticationToken authenticationToken) {

}
