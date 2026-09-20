package com.inkblogdb.ddd.infrastructure.database.query.song;

import java.time.LocalDateTime;

public record SongQueryRecordEntity(
    String id,
    String name,
    int level,
    LocalDateTime publishAt,
    int unlockStatus,
    LocalDateTime unlockedAt
) {

}
