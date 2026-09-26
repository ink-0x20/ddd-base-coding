package com.inkblogdb.ddd.domain.model.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.Whole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class PlayerItem {

  private final PlayerId playerId;
  private final ItemId itemId;
  private PlayerItemQuantity playerItemQuantity;

  public static PlayerItem createNotHave(PlayerId playerId, ItemId itemId) {
    return new PlayerItem(
        playerId,
        itemId,
        new PlayerItemQuantity(0)
    );
  }

  public boolean canConsume(Whole quantity) {
    return playerItemQuantity.isGreaterThanOrEqual(quantity);
  }

  public void consume(Whole quantity) {
    this.playerItemQuantity = playerItemQuantity.minus(quantity);

  }

}
