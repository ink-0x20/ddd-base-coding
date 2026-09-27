package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItem;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemQuantity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayItemDomainFactory {

  public Optional<PlayerItem> createFrom(PlayerItemRecordEntity playerItemRecordEntity) {
    if (playerItemRecordEntity == null) {
      return Optional.empty();
    }
    return Optional.of(new PlayerItem(
        new PlayerId(playerItemRecordEntity.playerId()),
        new ItemId(playerItemRecordEntity.itemId()),
        new PlayerItemQuantity(playerItemRecordEntity.quantity())
    ));
  }

}
