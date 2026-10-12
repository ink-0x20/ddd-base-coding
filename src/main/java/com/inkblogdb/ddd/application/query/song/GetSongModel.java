package com.inkblogdb.ddd.application.query.song;

import java.time.LocalDateTime;

public record GetSongModel(
    String id,
    String name,
    int easyLevel,
    int normalLevel,
    int hardLevel,
    int expertLevel,
    int unlockType,
    String unlockTargetId,
    int unlockQuantity,
    LocalDateTime releaseDateTime,
    Integer unlockStatus,
    LocalDateTime unlockedAt
) {

}
