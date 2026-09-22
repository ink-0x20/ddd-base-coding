package com.inkblogdb.ddd.presentation.api.v1.song;

import java.time.LocalDateTime;

public record SongResponse(
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
    int unlockStatus,
    LocalDateTime unlockedAt
) {

}
