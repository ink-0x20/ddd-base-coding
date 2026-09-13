package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PlayerDomainFactoryTest {

  @InjectMocks
  private PlayerDomainFactory playerDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 引数に値がある場合プレイヤーが取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerRecordEntity entity = new PlayerRecordEntity(
          playerId.value(),
          "テストプレイヤー名"
      );

      // when
      Optional<Player> result = playerDomainFactory.createFrom(entity);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId.value(), result.get().playerId().value());
      assertEquals("テストプレイヤー名", result.get().playerName().value());
    }

    @Test
    void 引数がnullの場合プレイヤーが空になること() {
      // when
      Optional<Player> result = playerDomainFactory.createFrom(null);

      // then
      assertTrue(result.isEmpty());
    }
  }

}
