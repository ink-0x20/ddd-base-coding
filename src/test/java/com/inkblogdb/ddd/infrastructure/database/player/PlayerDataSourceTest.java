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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
      when(playerDomainFactory.createFrom(any())).thenReturn(Optional.of(player));

      // when
      Optional<Player> result = playerDataSource.findById(playerId);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId, result.get().playerId());
      assertEquals("テストプレイヤー名", result.get().playerName().value());
    }

    @Test
    void DBに値がない場合プレイヤーが空になること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(playerMapper.findById(any())).thenReturn(null);

      // when
      Optional<Player> result = playerDataSource.findById(playerId);

      // then
      assertTrue(result.isEmpty());
    }
  }

  @Nested
  class addPlayer {
    @Test
    void プレイヤーを保存できること() {
      // given
      Player player = new Player(
          PlayerId.generate(),
          new PlayerName("テストプレイヤー名")
      );

      // when then
      assertDoesNotThrow(() -> playerDataSource.addPlayer(player));
    }
  }

}
