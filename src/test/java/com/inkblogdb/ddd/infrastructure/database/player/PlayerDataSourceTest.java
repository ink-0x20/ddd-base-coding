package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerDataSourceTest {

  @InjectMocks
  private PlayerDataSource playerDataSource;
  @Mock
  private PlayerMapper playerMapper;
  @Mock
  private PlayerDomainFactory playerDomainFactory;

  @Nested
  class findById {
    @Test
    void DBに値がある場合プレイヤーが取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      Player player = new Player(
          playerId,
          new PlayerName("テストプレイヤー名")
      );
      when(playerDomainFactory.createFrom(any())).thenReturn(player);

      // when
      Player result = playerDataSource.findById(playerId);

      // then
      assertNotNull(result);
      assertEquals(playerId, result.playerId());
      assertEquals("テストプレイヤー名", result.playerName().value());
    }

    @Test
    void DBに値がない場合プレイヤーが空になること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(playerMapper.findById(any())).thenReturn(null);

      // when
      Player result = playerDataSource.findById(playerId);

      // then
      assertNull(result);
    }
  }

  @Nested
  class addPlayer {
    @Test
    void プレイヤーを保存できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");

      // when
      playerDataSource.addPlayer(playerId, playerName);

      // then
      verify(playerMapper).addPlayer(any());
    }
  }

}
