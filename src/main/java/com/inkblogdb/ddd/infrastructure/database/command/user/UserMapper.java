package com.inkblogdb.ddd.infrastructure.database.command.user;

import com.inkblogdb.ddd.domain.type.Existence;

import java.util.UUID;

public interface UserMapper {

  Existence existsByUserId(UUID id);

  Existence existsByPlayerId(String playerId);

  UserRecordEntity findById(UUID id);

  void addUser(UserRecordEntity userRecordEntity);

}
