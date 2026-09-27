package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import java.util.UUID;

public interface PlayerItemMapper {

  PlayerItemRecordEntity findById(String playerId, UUID itemId);

  void updateQuantity(PlayerItemRecordEntity playerItemRecordEntity);

}
