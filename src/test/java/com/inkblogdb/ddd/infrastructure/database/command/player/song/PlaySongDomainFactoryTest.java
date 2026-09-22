package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongUnlockedDateTime;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlaySongDomainFactoryTest {

  @InjectMocks
  private PlaySongDomainFactory playSongDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      PlayerSongUnlockedDateTime playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
      PlayerSongRecordEntity playerSongRecordEntity = new PlayerSongRecordEntity(
          playerId.value(),
          songId.value(),
          UnlockStatus.LOCKED.value(),
          playerSongUnlockedDateTime.value()
      );

      // when
      PlayerSong result = playSongDomainFactory.createFrom(playerSongRecordEntity);

      // then
      assertNotNull(result);
      assertEquals(playerId.value(), result.playerId().value());
      assertEquals(songId.value(), result.songId().value());
      assertEquals(UnlockStatus.LOCKED, result.unlockStatus());
      assertEquals(playerSongUnlockedDateTime.value(), result.playerSongUnlockedDateTime().value());
    }

    @Test
    void nullの場合エラーになること() {
      // when then
      assertThrows(InfrastructureException.class, () -> playSongDomainFactory.createFrom(null));
    }
  }

}
