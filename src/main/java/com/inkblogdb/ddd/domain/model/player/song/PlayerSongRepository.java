package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.Existence;

public interface PlayerSongRepository {

  Existence exists(PlayerId playerId, SongId songId);

  PlayerSong findById(PlayerId playerId, SongId songId);

  void updateUnlockStatus(PlayerSong playerSong);

}
