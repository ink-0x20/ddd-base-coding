package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.unlock.SongUnlockStatus;
import com.inkblogdb.ddd.domain.model.player.song.SongUnlockedDateTime;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

@Component
public class PlaySongDomainFactory {

  public PlayerSong createFrom(PlayerSongRecordEntity playerSongRecordEntity) {
    if (playerSongRecordEntity == null) {
      throw new InfrastructureException("プレイヤー楽曲レコードが存在しない");
    }
    return new PlayerSong(
        new PlayerId(playerSongRecordEntity.playerId()),
        new SongId(playerSongRecordEntity.songId()),
        SongUnlockStatus.createFrom(playerSongRecordEntity.unlockStatus()),
        new SongUnlockedDateTime(playerSongRecordEntity.songUnlockedDateTime())
    );
  }

}
