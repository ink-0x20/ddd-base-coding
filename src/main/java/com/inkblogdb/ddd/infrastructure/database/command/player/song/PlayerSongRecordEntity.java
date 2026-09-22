package com.inkblogdb.ddd.infrastructure.database.command.player.song;

import java.time.LocalDateTime;

public record PlayerSongRecordEntity(
    String playerId,
    String songId,
    int unlockStatus,
    LocalDateTime songUnlockedDateTime
) {

}
