package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

public interface UserRepository {

  boolean existsByUserId(UserId userId);

  boolean notExistsByUserId(UserId userId);

  boolean notExistsByPlayerId(PlayerId playerId);

  User findById(UserId userId);

  void addUser(UserId userId, PlayerId playerId);

}
