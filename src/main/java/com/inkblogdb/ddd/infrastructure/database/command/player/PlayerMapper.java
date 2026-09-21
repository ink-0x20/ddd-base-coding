package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.domain.type.Existence;

public interface PlayerMapper {

  Existence exists(String id);

  PlayerRecordEntity findById(String id);

  void addPlayer(PlayerRecordEntity playerRecordEntity);

}
