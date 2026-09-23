package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongUnlockedDateTime;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerSongDataSourceTest {

  @InjectMocks
  private PlayerSongDataSource playerSongDataSource;
  @Mock
  private PlayerSongMapper playerSongMapper;
  @Mock
  private PlaySongDomainFactory playSongDomainFactory;

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      PlayerSongUnlockedDateTime playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          UnlockStatus.LOCKED,
          playerSongUnlockedDateTime
      );
      when(playSongDomainFactory.createFrom(any())).thenReturn(Optional.of(playerSong));

      // when
      var result = playerSongDataSource.findById(playerId, songId);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId, result.get().playerId());
      assertEquals(songId, result.get().songId());
      assertEquals(UnlockStatus.LOCKED, result.get().unlockStatus());
      assertEquals(playerSongUnlockedDateTime, result.get().playerSongUnlockedDateTime());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      when(playSongDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> playerSongDataSource.findById(playerId, songId));
    }
  }

  @Nested
  class updateUnlockStatus {
    @Test
    void 値が保存できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      PlayerSongUnlockedDateTime playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          UnlockStatus.LOCKED,
          playerSongUnlockedDateTime
      );

      // when
      playerSongDataSource.insertUnlockStatus(playerSong);

      // then
      verify(playerSongMapper).insertUnlockStatus(any());
    }

    @Test
    void 失敗した場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      PlayerSongUnlockedDateTime playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          UnlockStatus.LOCKED,
          playerSongUnlockedDateTime
      );
      doThrow(InfrastructureException.class).when(playerSongMapper).insertUnlockStatus(any());

      // when then
      assertThrows(InfrastructureException.class, () -> playerSongDataSource.insertUnlockStatus(playerSong));
    }
  }

}
