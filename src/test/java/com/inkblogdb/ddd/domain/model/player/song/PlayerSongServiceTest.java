package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerSongServiceTest {

  @InjectMocks
  private PlayerSongService playerSongService;
  @Mock
  private PlayerSongRepository playerSongRepository;

  @Test
  void 取得できること() {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    PlayerSongUnlockedDateTime playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
    PlayerSong playerSong = new PlayerSong(
        playerId,
        songId,
        UnlockStatus.UNLOCKED,
        playerSongUnlockedDateTime
    );
    when(playerSongRepository.findById(any(), any())).thenReturn(Optional.of(playerSong));

    // when
    var result = playerSongService.findById(playerId, songId);

    // then
    assertEquals(playerId, result.playerId());
    assertEquals(songId, result.songId());
    assertEquals(UnlockStatus.UNLOCKED, result.unlockStatus());
    assertEquals(playerSongUnlockedDateTime, result.playerSongUnlockedDateTime());
  }

  @Test
  void データがなかった場合空のデータを取得できること() {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    when(playerSongRepository.findById(any(), any())).thenReturn(Optional.empty());

    // when
    var result = playerSongService.findById(playerId, songId);

    // then
    assertEquals(playerId, result.playerId());
    assertEquals(songId, result.songId());
    assertEquals(UnlockStatus.LOCKED, result.unlockStatus());
  }

}
