package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.Exists;

public interface UserRepository {

  Exists existsByUserId(UserId userId);

  Exists existsByPlayerId(PlayerId playerId);

  User findById(UserId userId);

  void addUser(UserId userId, PlayerId playerId);

}
