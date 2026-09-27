package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerSongUnlockHandleFactoryTest {

  @InjectMocks
  private PlayerSongUnlockHandleFactory playerSongUnlockHandleFactory;

  @Test
  void nullの場合エラーになること() {
    // when
    var result = assertThrows(DomainException.class, () -> playerSongUnlockHandleFactory.createHandler(null));

    // then
    assertEquals("プレイヤー楽曲解放操作が空", result.getMessage());
  }

  @Test
  void チケットで解放する場合ハンドラーを生成できること() {
    // given
    PlayerSongUnlockAction playerSongUnlockAction = PlayerSongUnlockAction.UNLOCK_BY_TICKET;

    // when
    var result = playerSongUnlockHandleFactory.createHandler(playerSongUnlockAction);

    // then
    assertInstanceOf(PlayerSongUnlockByTicketHandler.class, result);
  }

}
