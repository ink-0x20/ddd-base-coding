package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongUnlockedDateTime;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlaySongDomainFactory {

  public Optional<PlayerSong> createFrom(PlayerSongRecordEntity playerSongRecordEntity) {
    if (playerSongRecordEntity == null) {
      return Optional.empty();
    }
    return Optional.of(new PlayerSong(
        new PlayerId(playerSongRecordEntity.playerId()),
        new SongId(playerSongRecordEntity.songId()),
        UnlockStatus.createFrom(playerSongRecordEntity.unlockStatus()),
        new PlayerSongUnlockedDateTime(playerSongRecordEntity.playerSongUnlockedDateTime())
    ));
  }

}
