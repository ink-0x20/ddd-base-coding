package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.Existence;

public interface UserRepository {

  Existence existsByUserId(UserId userId);

  Existence existsByPlayerId(PlayerId playerId);

  User findById(UserId userId);

  void addUser(UserId userId, PlayerId playerId);

}
