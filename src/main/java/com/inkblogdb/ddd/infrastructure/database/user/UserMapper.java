package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.type.Exists;

import java.util.UUID;

public interface UserMapper {

  Exists existsByUserId(UUID id);

  Exists existsByPlayerId(String playerId);

  UserRecordEntity findById(UUID id);

  void addUser(UserRecordEntity userRecordEntity);

}
