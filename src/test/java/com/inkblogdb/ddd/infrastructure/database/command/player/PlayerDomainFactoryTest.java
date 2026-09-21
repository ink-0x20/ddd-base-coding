package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PlayerDomainFactoryTest {

  @InjectMocks
  private PlayerDomainFactory playerDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      PlayerRecordEntity players = new PlayerRecordEntity(
          "zzzzzzzz",
          "テストプレイヤー"
      );

      // when
      var result = playerDomainFactory.createFrom(players);

      // then
      assertEquals("zzzzzzzz", result.playerId().value());
      assertEquals("テストプレイヤー", result.playerName().value());
    }

    @Test
    void nullの場合エラーになること() {
      // when then
      assertThrows(InfrastructureException.class, () -> playerDomainFactory.createFrom(null));
    }
  }

}
