package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongRepository;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerSongDataSource implements PlayerSongRepository {

  private final PlayerSongMapper playerSongMapper;
  private final PlaySongDomainFactory playSongDomainFactory;

  @Override
  public Optional<PlayerSong> findById(PlayerId playerId, SongId songId) {
    return playSongDomainFactory.createFrom(
        playerSongMapper.findById(playerId.value(), songId.value())
    );
  }

  @Override
  public void insertUnlockStatus(PlayerSong playerSong) {
    PlayerSongRecordEntity entity = new PlayerSongRecordEntity(
        playerSong.playerId().value(),
        playerSong.songId().value(),
        playerSong.unlockStatus().value(),
        playerSong.playerSongUnlockedDateTime().value()
    );
    try {
      playerSongMapper.insertUnlockStatus(entity);
    } catch (Exception e) {
      throw new InfrastructureException(e);
    }
  }

}
