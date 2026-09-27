package com.inkblogdb.ddd.application.usecase.player.song;

import com.inkblogdb.ddd.application.usecase.abort.FailedPlayerSongUnlockException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongRepository;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongService;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongUnlockedDateTime;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockByTicketHandler;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockHandleFactory;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import com.inkblogdb.ddd.domain.type.Unlockability;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnlockSongUseCaseTest {

  @InjectMocks
  private UnlockSongUseCase unlockSongUseCase;
  @Mock
  private PlayerSongRepository playerSongRepository;
  @Mock
  private PlayerSongService playerSongService;
  @Mock
  private PlayerSongUnlockHandleFactory playerSongUnlockHandleFactory;
  @Mock
  private PlayerSongUnlockByTicketHandler playerSongUnlockByTicketHandler;

  @Test
  void 解放できること() throws FailedPlayerSongUnlockException {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    UnlockSongCommand unlockSongCommand = new UnlockSongCommand(
        playerId.value(),
        songId.value(),
        "UNLOCK_BY_TICKET"
    );
    PlayerSong playerSong = PlayerSong.createWithLocked(playerId, songId);
    when(playerSongService.findById(any(), any())).thenReturn(playerSong);
    when(playerSongUnlockHandleFactory.createHandler(any())).thenReturn(playerSongUnlockByTicketHandler);
    when(playerSongUnlockByTicketHandler.canUnlock(any(), any())).thenReturn(Unlockability.UNLOCKABLE);

    // when
    unlockSongUseCase.unlock(unlockSongCommand);

    // then
    verify(playerSongRepository).insertUnlockStatus(any());
  }

  @Test
  void 既に解放済みの場合更新せず終了すること() throws FailedPlayerSongUnlockException {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    UnlockSongCommand unlockSongCommand = new UnlockSongCommand(
        playerId.value(),
        songId.value(),
        "UNLOCK_BY_TICKET"
    );
    PlayerSong playerSong = new PlayerSong(
        playerId,
        songId,
        UnlockStatus.UNLOCKED,
        PlayerSongUnlockedDateTime.now()
    );
    when(playerSongService.findById(any(), any())).thenReturn(playerSong);

    // when
    unlockSongUseCase.unlock(unlockSongCommand);

    // then
    verify(playerSongRepository, never()).insertUnlockStatus(any());
  }

  @Test
  void 解放条件に合致していない場合エラーになること() throws FailedPlayerSongUnlockException {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    UnlockSongCommand unlockSongCommand = new UnlockSongCommand(
        playerId.value(),
        songId.value(),
        "UNLOCK_BY_TICKET"
    );
    PlayerSong playerSong = PlayerSong.createWithLocked(playerId, songId);
    when(playerSongService.findById(any(), any())).thenReturn(playerSong);
    when(playerSongUnlockHandleFactory.createHandler(any())).thenReturn(playerSongUnlockByTicketHandler);
    when(playerSongUnlockByTicketHandler.canUnlock(any(), any())).thenReturn(Unlockability.LOCKED);

    // when then
    assertEquals(
        "プレイヤー楽曲の解放に失敗",
        assertThrows(FailedPlayerSongUnlockException.class, () -> unlockSongUseCase.unlock(unlockSongCommand)).getMessage()
    );
  }

}
