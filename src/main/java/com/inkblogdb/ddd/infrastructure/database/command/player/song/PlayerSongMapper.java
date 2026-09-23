package com.inkblogdb.ddd.infrastructure.database.command.player.song;

public interface PlayerSongMapper {

  PlayerSongRecordEntity findById(String playerId, String songId);

  void insertUnlockStatus(PlayerSongRecordEntity playerSongRecordEntity);

}
