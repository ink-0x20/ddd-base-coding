package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;

import java.util.Optional;

public interface PlayerSongRepository {

  Optional<PlayerSong> findById(PlayerId playerId, SongId songId);

  void insertUnlockStatus(PlayerSong playerSong);

}
