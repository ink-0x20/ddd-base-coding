package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemQuantity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PlayItemDomainFactoryTest {

  @InjectMocks
  private PlayItemDomainFactory playItemDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      ItemId itemId = new ItemId(UUID.randomUUID());
      PlayerItemQuantity playerItemQuantity = new PlayerItemQuantity(1);
      PlayerItemRecordEntity playerItemRecordEntity = new PlayerItemRecordEntity(
          playerId.value(),
          itemId.value(),
          playerItemQuantity.value()
      );

      // when
      var result = playItemDomainFactory.createFrom(playerItemRecordEntity);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId.value(), result.get().playerId().value());
      assertEquals(itemId.value(), result.get().itemId().value());
      assertEquals(playerItemQuantity.value(), result.get().playerItemQuantity().value());
    }

    @Test
    void nullの場合空になること() {
      // when
      var result = playItemDomainFactory.createFrom(null);

      // then
      assertTrue(result.isEmpty());
    }
  }

}
