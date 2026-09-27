package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.Unlockability;

public interface PlayerSongUnlockHandler {

  Unlockability canUnlock(PlayerId playerId, SongId songId);

  void executeUnlockAction(PlayerId playerId, SongId songId);

}
