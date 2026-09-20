package com.inkblogdb.ddd.application.query.song;

import java.time.LocalDateTime;

public record SongDTO(
    String id,
    String name,
    int level,
    LocalDateTime releaseDateTime,
    int unlockStatus,
    LocalDateTime unlockedAt
) {

}
