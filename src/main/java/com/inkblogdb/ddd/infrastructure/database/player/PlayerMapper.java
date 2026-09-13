package com.inkblogdb.ddd.infrastructure.database.player;

public interface PlayerMapper {

  PlayerRecordEntity findById(String id);

  void addPlayer(PlayerRecordEntity playerRecordEntity);

}
