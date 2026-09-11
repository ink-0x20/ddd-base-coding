package com.inkblogdb.ddd.infrastructure.database.song;

import java.time.LocalDateTime;

public record SongRecordEntity(
    String id,
    String name,
    int level,
    LocalDateTime publishAt) {

}
