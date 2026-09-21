package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
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
class PlayerDataSourceTest {

  @InjectMocks
  private PlayerDataSource playerDataSource;
  @Mock
  private PlayerMapper playerMapper;
  @Mock
  private PlayerDomainFactory playerDomainFactory;

  @Nested
  class exists {
    @Test
    void 存在すること() {
      // given
      when(playerMapper.exists(any())).thenReturn(Existence.YES);

      // when
      Existence result = playerDataSource.exists(PlayerId.generate());

      // then
      assertTrue(result.yes());
    }

    @Test
    void 存在しないこと() {
      // given
      when(playerMapper.exists(any())).thenReturn(Existence.NO);

      // when
      Existence result = playerDataSource.exists(PlayerId.generate());

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
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      when(playerDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> playerDataSource.findById(playerId));
    }
  }

  @Nested
  class addPlayer {
    @Test
    void 値が保存できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");

      // when
      playerDataSource.addPlayer(playerId, playerName);

      // then
      verify(playerMapper).addPlayer(any());
    }

    @Test
    void 失敗した場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");
      doThrow(InfrastructureException.class).when(playerMapper).addPlayer(any());

      // when then
      assertThrows(InfrastructureException.class, () -> playerDataSource.addPlayer(playerId, playerName));
    }
  }

}
