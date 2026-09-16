package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.song.SongUnlockStatus;
import com.inkblogdb.ddd.domain.model.song.Songs;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import com.inkblogdb.ddd.infrastructure.database.song.SongDomainFactory;
import com.inkblogdb.ddd.infrastructure.database.song.SongRecordEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerDomainFactoryTest {

  @InjectMocks
  private SongDomainFactory songDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      List<SongRecordEntity> songs = List.of(
          new SongRecordEntity(
              "zzzzzzzz",
              "テスト楽曲",
              5,
              LocalDateTime.now(),
              SongUnlockStatus.UNLOCKED.value(),
              LocalDateTime.now()
          )
      );

      // when
      Songs result = songDomainFactory.createFrom(songs);

      // then
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
    void nullの場合エラーになること() {
      // when then
      assertThrows(InfrastructureException.class, () -> songDomainFactory.createFrom(null));
    }
  }

}
