package com.inkblogdb.ddd.infrastructure.database.command.song;

import java.time.LocalDateTime;
import java.util.UUID;

public record SongRecordEntity(
    String id,
    String name,
    int easyLevel,
    int normalLevel,
    int hardLevel,
    int expertLevel,
    int unlockType,
    UUID unlockTargetId,
    int unlockQuantity,
    LocalDateTime publishAt
) {

}
