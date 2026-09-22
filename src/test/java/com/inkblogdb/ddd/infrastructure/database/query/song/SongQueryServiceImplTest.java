package com.inkblogdb.ddd.infrastructure.database.query.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.unlock.SongUnlockStatus;
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
class SongQueryServiceImplTest {

  @InjectMocks
  private SongQueryServiceImpl songQueryServiceImpl;
  @Mock
  private SongQueryMapper songQueryMapper;

  @Nested
  class findAll {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      List<SongQueryRecordEntity> songs = List.of(
          new SongQueryRecordEntity(
              "zzzzzzzz",
              "テスト楽曲",
              1,
              2,
              3,
              4,
              0,
              null,
              0,
              LocalDateTime.now(),
              SongUnlockStatus.UNLOCKED.value(),
              LocalDateTime.now()
          )
      );
      when(songQueryMapper.findAll(any())).thenReturn(songs);

      // when
      var result = songQueryServiceImpl.findAll(playerId);

      // then
      assertNotNull(result);
      assertEquals(1, result.size());
      assertTrue(result.stream().findFirst().isPresent());
      assertEquals("zzzzzzzz", result.stream().findFirst().get().id());
      assertEquals("テスト楽曲", result.stream().findFirst().get().name());
      assertEquals(1, result.stream().findFirst().get().easyLevel());
      assertEquals(2, result.stream().findFirst().get().normalLevel());
      assertEquals(3, result.stream().findFirst().get().hardLevel());
      assertEquals(4, result.stream().findFirst().get().expertLevel());
      assertEquals(0, result.stream().findFirst().get().unlockType());
      assertNull(result.stream().findFirst().get().unlockTargetId());
      assertEquals(0, result.stream().findFirst().get().unlockQuantity());
      assertNotNull(result.stream().findFirst().get().releaseDateTime());
      assertEquals(SongUnlockStatus.UNLOCKED.value(), result.stream().findFirst().get().unlockStatus());
      assertNotNull(result.stream().findFirst().get().unlockedAt());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(songQueryMapper.findAll(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> songQueryServiceImpl.findAll(playerId));
    }
  }

}
