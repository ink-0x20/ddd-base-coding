package com.inkblogdb.ddd.infrastructure.database.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.*;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongDataSourceTest {

  @InjectMocks
  private SongDataSource songDataSource;
  @Mock
  private SongMapper songMapper;
  @Mock
  private SongDomainFactory songDomainFactory;

  @Nested
  class findAll {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      Songs songs = new Songs(List.of(
          new Song(
              new SongId("zzzzzzzz"),
              new SongName("テスト楽曲"),
              new SongLevel(5),
              new SongReleaseDateTime(LocalDateTime.now()),
              SongUnlockStatus.UNLOCKED,
              new SongUnlockedDateTime(LocalDateTime.now())
          )
      ));
      when(songDomainFactory.createFrom(any())).thenReturn(songs);

      // when
      Songs result = songDataSource.findAll(playerId);

      // then
      assertNotNull(result);
      assertEquals(1, result.songs().size());
      assertTrue(result.songs().stream().findFirst().isPresent());
      assertEquals("zzzzzzzz", result.songs().stream().findFirst().get().songId().value());
      assertEquals("テスト楽曲", result.songs().stream().findFirst().get().songName().value());
      assertEquals(5, result.songs().stream().findFirst().get().songLevel().value());
      assertNotNull(result.songs().stream().findFirst().get().songReleaseDateTime().value());
      assertEquals(SongUnlockStatus.UNLOCKED.value(), result.songs().stream().findFirst().get().songUnlockStatus().value());
      assertNotNull(result.songs().stream().findFirst().get().songUnlockedDateTime().value());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(songDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> songDataSource.findAll(playerId));
    }
  }

}
