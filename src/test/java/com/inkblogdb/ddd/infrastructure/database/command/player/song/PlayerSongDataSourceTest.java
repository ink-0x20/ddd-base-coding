package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.SongUnlockStatus;
import com.inkblogdb.ddd.domain.model.player.song.SongUnlockedDateTime;
import com.inkblogdb.ddd.domain.type.Existence;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
  class exists {
    @Test
    void 存在すること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      when(playerSongMapper.exists(any(), any())).thenReturn(Existence.YES);

      // when
      Existence result = playerSongDataSource.exists(playerId, songId);

      // then
      assertTrue(result.yes());
    }

    @Test
    void 存在しないこと() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      when(playerSongMapper.exists(any(), any())).thenReturn(Existence.NO);

      // when
      Existence result = playerSongDataSource.exists(playerId, songId);

      // then
      assertTrue(result.no());
    }
  }

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      SongUnlockedDateTime songUnlockedDateTime = SongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          SongUnlockStatus.UNLOCKED,
          songUnlockedDateTime
      );
      when(playSongDomainFactory.createFrom(any())).thenReturn(playerSong);

      // when
      PlayerSong result = playerSongDataSource.findById(playerId, songId);

      // then
      assertNotNull(result);
      assertEquals(playerId, result.playerId());
      assertEquals(songId, result.songId());
      assertEquals(SongUnlockStatus.UNLOCKED, result.songUnlockStatus());
      assertEquals(songUnlockedDateTime, result.songUnlockedDateTime());
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
      SongUnlockedDateTime songUnlockedDateTime = SongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          SongUnlockStatus.UNLOCKED,
          songUnlockedDateTime
      );

      // when
      playerSongDataSource.updateUnlockStatus(playerSong);

      // then
      verify(playerSongMapper).updateUnlockStatus(any());
    }

    @Test
    void 失敗した場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = SongId.generate();
      SongUnlockedDateTime songUnlockedDateTime = SongUnlockedDateTime.now();
      PlayerSong playerSong = new PlayerSong(
          playerId,
          songId,
          SongUnlockStatus.UNLOCKED,
          songUnlockedDateTime
      );
      doThrow(InfrastructureException.class).when(playerSongMapper).updateUnlockStatus(any());

      // when then
      assertThrows(InfrastructureException.class, () -> playerSongDataSource.updateUnlockStatus(playerSong));
    }
  }

}
