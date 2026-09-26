package com.inkblogdb.ddd.domain.model.player.item;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;

import java.util.Optional;

public interface PlayerItemRepository {

  Optional<PlayerItem> findById(PlayerId playerId, ItemId itemId);

  void updateQuantity(PlayerItem playerItem);

}
