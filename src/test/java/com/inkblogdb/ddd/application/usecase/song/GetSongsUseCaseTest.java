package com.inkblogdb.ddd.application.usecase.song;

import com.inkblogdb.ddd.application.dto.song.SongDTO;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSongsUseCaseTest {

  @InjectMocks
  private GetSongsUseCase getSongsUseCase;
  @Mock
  private SongRepository songRepository;

  @Test
  void 楽曲一覧を取得できること() {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    LocalDateTime now = LocalDateTime.now();
    Songs songs = new Songs(
        List.of(
            new Song(
                songId,
                new SongName("テスト楽曲名"),
                new SongLevel(1),
                new SongReleaseDateTime(now),
                SongUnlockStatus.UNLOCKED,
                new SongUnlockedDateTime(now)
            )
        )
    );
    when(songRepository.findAll(any())).thenReturn(songs);

    // when
    List<SongDTO> result = getSongsUseCase.getSongs(playerId);

    // then
    assertEquals(1, result.size());
    assertTrue(result.stream().findFirst().isPresent());
    assertEquals(songId.value(), result.stream().findFirst().get().id());
    assertEquals("テスト楽曲名", result.stream().findFirst().get().name());
    assertEquals(1, result.stream().findFirst().get().level());
    assertEquals(now, result.stream().findFirst().get().releaseDateTime());
    assertEquals(SongUnlockStatus.UNLOCKED.value(), result.stream().findFirst().get().unlockStatus());
    assertEquals(now, result.stream().findFirst().get().unlockedAt());
  }

}
