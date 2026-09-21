package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.domain.type.Exists;

public interface PlayerMapper {

  Exists exists(String id);

  PlayerRecordEntity findById(String id);

  void addPlayer(PlayerRecordEntity playerRecordEntity);

}
