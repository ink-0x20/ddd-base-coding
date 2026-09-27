package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongUnlockedDateTime;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
      var result = playSongDomainFactory.createFrom(playerSongRecordEntity);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId.value(), result.get().playerId().value());
      assertEquals(songId.value(), result.get().songId().value());
      assertEquals(UnlockStatus.LOCKED, result.get().unlockStatus());
      assertEquals(playerSongUnlockedDateTime.value(), result.get().playerSongUnlockedDateTime().value());
    }

    @Test
    void nullの場合空になること() {
      // when
      var result = playSongDomainFactory.createFrom(null);

      // then
      assertTrue(result.isEmpty());
    }
  }

}
