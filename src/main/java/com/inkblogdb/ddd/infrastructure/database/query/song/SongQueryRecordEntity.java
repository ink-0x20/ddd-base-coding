package com.inkblogdb.ddd.infrastructure.database.query.song;

import java.time.LocalDateTime;

public record SongQueryRecordEntity(
    String id,
    String name,
    int easyLevel,
    int normalLevel,
    int hardLevel,
    int expertLevel,
    int unlockType,
    String unlockTargetId,
    int unlockQuantity,
    LocalDateTime publishAt,
    int unlockStatus,
    LocalDateTime unlockedAt
) {

}
