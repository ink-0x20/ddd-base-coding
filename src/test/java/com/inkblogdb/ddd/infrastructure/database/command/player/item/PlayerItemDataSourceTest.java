package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItem;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemQuantity;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerItemDataSourceTest {

  @InjectMocks
  private PlayerItemDataSource playerItemDataSource;
  @Mock
  private PlayerItemMapper playerItemMapper;
  @Mock
  private PlayItemDomainFactory playItemDomainFactory;

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      ItemId itemId = new ItemId(UUID.randomUUID());
      PlayerItemQuantity playerItemQuantity = new PlayerItemQuantity(1);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          itemId,
          playerItemQuantity
      );
      when(playItemDomainFactory.createFrom(any())).thenReturn(Optional.of(playerItem));

      // when
      var result = playerItemDataSource.findById(playerId, itemId);

      // then
      assertTrue(result.isPresent());
      assertEquals(playerId, result.get().playerId());
      assertEquals(itemId, result.get().itemId());
      assertEquals(playerItemQuantity, result.get().playerItemQuantity());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      ItemId itemId = new ItemId(UUID.randomUUID());
      when(playItemDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> playerItemDataSource.findById(playerId, itemId));
    }
  }

  @Nested
  class updateQuantity {
    @Test
    void 値が保存できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      ItemId itemId = new ItemId(UUID.randomUUID());
      PlayerItemQuantity playerItemQuantity = new PlayerItemQuantity(1);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          itemId,
          playerItemQuantity
      );

      // when
      playerItemDataSource.updateQuantity(playerItem);

      // then
      verify(playerItemMapper).updateQuantity(any());
    }

    @Test
    void 失敗した場合エラーになること() {
      // given
      PlayerId playerId = PlayerId.generate();
      ItemId itemId = new ItemId(UUID.randomUUID());
      PlayerItemQuantity playerItemQuantity = new PlayerItemQuantity(1);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          itemId,
          playerItemQuantity
      );
      doThrow(InfrastructureException.class).when(playerItemMapper).updateQuantity(any());

      // when then
      assertThrows(InfrastructureException.class, () -> playerItemDataSource.updateQuantity(playerItem));
    }
  }

}
