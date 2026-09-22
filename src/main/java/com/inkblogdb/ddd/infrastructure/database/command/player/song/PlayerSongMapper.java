package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.type.Existence;

public interface PlayerSongMapper {

  Existence exists(String playerId, String songId);

  PlayerSongRecordEntity findById(String playerId, String songId);

  void updateUnlockStatus(PlayerSongRecordEntity playerSongRecordEntity);

}
