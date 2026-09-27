package com.inkblogdb.ddd.infrastructure.database.command.player.item;

import java.util.UUID;

public record PlayerItemRecordEntity(
    String playerId,
    UUID itemId,
    int quantity
) {

}
