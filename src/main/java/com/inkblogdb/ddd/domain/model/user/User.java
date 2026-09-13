package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

public record User(UserId userId, PlayerId playerId) {

}
