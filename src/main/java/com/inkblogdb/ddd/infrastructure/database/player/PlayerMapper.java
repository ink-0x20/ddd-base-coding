package com.inkblogdb.ddd.infrastructure.database.player;

public interface PlayerMapper {

  boolean exists(String id);

  PlayerRecordEntity findById(String id);

  void addPlayer(PlayerRecordEntity playerRecordEntity);

}
