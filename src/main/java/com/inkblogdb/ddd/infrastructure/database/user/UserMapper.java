package com.inkblogdb.ddd.infrastructure.database.user;

import java.util.UUID;

public interface UserMapper {

  boolean existsByUserId(UUID id);

  boolean existsByPlayerId(String playerId);

  UserRecordEntity findById(UUID id);

  void addUser(UserRecordEntity userRecordEntity);

}
